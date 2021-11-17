package com.hdu.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.util.failover.strategy.ConsulFailoverStrategy;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/3
 * @Time 下午6:56
 */
public class CustomBlackListingConsulFailoverStrategy implements ConsulFailoverStrategy {
    private Map<HostAndPort, Instant> blacklist = new HashMap();
    private Collection<HostAndPort> targets;
    private long timeout;

    public CustomBlackListingConsulFailoverStrategy(Collection<HostAndPort> targets, long timeout) {
        this.targets = targets;
        this.timeout = timeout;
    }

    public Optional<Request> computeNextStage(Request previousRequest, Response previousResponse) {
        HostAndPort initialTarget = this.fromRequest(previousRequest);
        if (previousResponse != null && (previousResponse.code() < 200 && previousResponse.code() >= 500)) {
            this.blacklist.put(initialTarget, Instant.now());
        }
        if (previousResponse != null && (previousResponse.code() >= 400 && previousResponse.code() < 500)) {
            return Optional.empty();
        }

        if (this.blacklist.containsKey(initialTarget)) {
            HostAndPort next = (HostAndPort) this.targets.stream().filter((target) -> {
                if (this.blacklist.containsKey(target)) {
                    Instant blacklistWhen = (Instant) this.blacklist.get(target);
                    if (!Duration.between(blacklistWhen, Instant.now()).minusMillis(this.timeout).isNegative()) {
                        this.blacklist.remove(target);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }).findAny().get();
            HttpUrl nextURL = previousRequest.url().newBuilder().host(next.getHost()).port(next.getPort()).build();
            return Optional.ofNullable(previousRequest.newBuilder().url(nextURL).build());
        } else {
            HttpUrl nextURL = previousRequest.url().newBuilder().host(initialTarget.getHost()).port(initialTarget.getPort()).build();
            return Optional.ofNullable(previousRequest.newBuilder().url(nextURL).build());
        }
    }

    public boolean isRequestViable(Request current) {
        return this.targets.size() > this.blacklist.size() || !this.blacklist.containsKey(this.fromRequest(current));
    }

    private HostAndPort fromRequest(Request request) {
        return HostAndPort.fromParts(request.url().host(), request.url().port());
    }

    public void markRequestFailed(Request current) {
        this.blacklist.put(this.fromRequest(current), Instant.now());
    }
}
