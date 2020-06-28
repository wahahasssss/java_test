package com.example.springapidemo.controller;

import com.example.springapidemo.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/23
 * @Time 上午10:36
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {
    private final Logger LOG = LoggerFactory.getLogger(TestController.class);
    private Integer integer = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @RequestMapping(value = "test")
    public String test(){
        System.out.println(this.toString());
        integer++;
        atomicInteger.incrementAndGet();
        System.out.println(integer);
        System.out.println("atomic" + atomicInteger.get());
        return "wahha";
    }
    @RequestMapping(value = "headers",method = {RequestMethod.POST,RequestMethod.GET})
    public HashMap<String, String> headers(HttpServletRequest request){
        Enumeration<String> names = request.getHeaderNames();
        HashMap<String,String>  headers = new HashMap<>();
        headers.put("ts",String.valueOf(System.currentTimeMillis()));
        headers.put("ip", IpUtils.getIpAddress(request));
        while (names.hasMoreElements()){
            String key = names.nextElement();
            headers.put(key,request.getHeader(key));
        }
        LOG.info(String.format("receive headers %s",headers.toString()));
        return headers;
    }


    @RequestMapping(value = "json1",method = {RequestMethod.POST,RequestMethod.GET})
    public String jsonOne(){
        return "{\n" +
                "  \"data\": {\n" +
                "    \"flag\": \"1\",\n" +
                "    \"map\": {\n" +
                "      \"BXP\": \"北京西\",\n" +
                "      \"CDW\": \"成都\",\n" +
                "      \"ICW\": \"成都东\"\n" +
                "    },\n" +
                "    \"result\": [\n" +
                "      \"zisjPgJIdkTFDtXX6D%2Fdh7ApeEggyOHn1QRS5nNIvwSjTLJRLRHaaKGxeYiwKBZT4D3WimDL4w2L%0ADnWDAoF3vDtztf0TQ9b6yEVw%2BKQwgm3SGDIsckiSzWlCEwY%2FDRt8Z%2BCUf7AlOG%2BAi5CCktrkjTGp%0AbqhYuDBW3WXHoRni2595VyB7adRf7e5Kx7A1PnWQu9NL%2BKcakaeFTs80ssEKPyWZur%2BpBliIErYt%0A2Mj0YAGuYVJOYiJyy%2BNmMahu7zTMiHiLXHxLXoH7ZF%2BNEaoUiHULgaYvSkOoYDHoicJvYYXqA%2BdV%0AhVu4mPUaliA%3D|预订|24000K42170B|K4217|BXP|CDW|BXP|CDW|01:56|21:57|44:01|Y|6UhLOzAiN5RdBIZjXUpHQ01%2Bo9vGK6MYF0BiUktoBZBOglEh37muuDJekaE%3D|20190201|3|PC|01|13|0|0||||无|||有||无|有|||||10401030|1413|1|1|3\",\n" +
                "      \"EIgby6Hg69T2TZUGLyoVybegYtTWI9q%2FteKpndoRLCXIBZoJ47eS0MKiYMtTsfrT8e7dvtoCMI1V%0ADxRy%2F1J%2FD0ZeSXGqY%2FlO%2BtlZAqWcqUYaLcwb2QN85PQ9KYJrLHN7BpSk1ijHnHeKjfIMhDHhpDZ%2F%0Ay9OWqY%2BXEIFwfiqhpHbuQ22ZreypfI2ykrX3bUYZwc93q3IesQMsy7yg5vihu30PJKUf6AVArSqf%0AScESjQ%2FIAHQSq79dzVchQAfHuqaVz4q6IH1I2gak904wqF%2BIt5mmpVryyAAw%2BWuXkT7uWOk%3D|预订|2400000G890C|G89|BXP|ICW|BXP|ICW|06:53|14:38|07:45|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P4|01|05|1|0|||||||||||无|无|无||O0M090|OM9|0|1|OM9\",\n" +
                "      \"VNbWr0i9RmqbmipyJHlUrmBrcLaIxPHzOJMDo1eAPs9hH79Y2jYtKeFpuxEIzhJ9CA7FvHYespaN%0A1VpHeqypjwRDgG5cpibNbHdbZ5atT0u8LV7pwaj1R4Cndnyby0Nolyx4ZIhsqDFAOK5I2x0pxd2%2B%0AG5iO8zvPjFDn1CVBdGzouOrTLhRa%2F%2FPVB5Y4b6VF%2FYGzbvEqE0zmHYtcTsD4I6%2Fey9xMoMFBe9WC%0AKv6f1pLZ62xjEVpeXdecWqFHJFIn74gnDkD8H7tJn6%2FwAW2Ba4nt7aYCcGwOyl31YZgtTbQ5tKHs%0AXlLeBg%3D%3D|预订|240000K8170X|K817|BXP|CDW|BXP|CDW|08:01|12:41|28:40|Y|%2FS%2BBzpJ9OKzUA7GGzKmnJPVnsSalRHDN7iW8kxNAclJbXogbvI7d8YcFP98%3D|20190201|3|PB|01|23|0|0||||无|||有||无|无|||||10401030|1413|0|1|413\",\n" +
                "      \"a%2Bt0J%2FKmu2WW7XRGp2gt2x6ndSw7oQk3QKvoGltGLR3oo2JpWsSFrE1fBmQFFGJT1CYwvdFYzn%2BC%0AFB4O%2FKpK%2FmfLefYJMun5gL54RBlk%2FxZ4vK5seV3WzkGpMRQgZp940X4djS3o7iGS3Lb73Pp4kWq%2F%0A0Jz7BkhUrtWR6ue91A%2BnA93tLuHLW09ZZwbPVoJycE2GQDo7S%2FFTmKMqc6TaKwv983I7c7nlstEc%0AeKY8qPZxyPy9FozSFBRSOAT8nunEHcSdWtmiJm4PZLynGpSKSHvoWheAMZi%2BvDwl8b0Wab1jI6w6%0A|预订|240000G5710S|G571|BXP|CXW|BXP|ICW|09:22|19:08|09:46|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P2|01|17|1|0|||||||||||无|无|无||O0M090|OM9|0|1|\",\n" +
                "      \"C6glElDHanDE7oTtc8KRIo1z%2Fv8sr9J85qgWyy8EKbPiiIYQoimkpNoqEaL6jYNJf1T2Obs1b4QM%0Al0OwUdZSlSDAGZqo%2Fh4Bxx0nqBJ7E2QZYCbkZEirqDheU%2Fp2YBxfv0TG0hq4NYNctw89jZhOKaMP%0AdtzJIiuDY%2FGr%2Fbd7pamfvDv6zqMGAMpYYZwVRLO0zg%2FhsxWW9k3xpJSCK2iBkrKKlofnuzi2sjDa%0AHy1C%2FW5IYoJ4rTvO0gP0CIQkBWJydJeNSkeroJyR1GxylklNGorFqsoPnUCG%2BiIhCYMLxBdncwLe%0A|预订|240000G3070J|G307|BXP|ICW|BXP|ICW|09:38|19:36|09:58|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P3|01|19|1|0|||||||||||无|无|无||O0M090|OM9|0|1|OM9\",\n" +
                "      \"yOTBqKCnboxFJ1TKR35S8vFEVzaKfUZKjSubwhbvsd542SICav9oS3T5jjgoNs5xzNQRr0qi8Vuv%0AYQ0kaTtGMEL9PMD3vl%2BUzjF6tGZuZvNogXJB5JXG%2F1MlGB%2FFaUquQ7dAIEsxrWQDF4KU7gj2Pa0p%0AgVz15rCvMsZRN5TF8QEVj24XAh%2BM4vH07JIXfaAMBdXNwfEcM7lqEa4EBTmDPT8gU35U%2FWZjkUoq%0Akm%2BLyF4ckdXqY6tdXTDdjLSc6Yxexm1K%2B7IVjFvFH4snFGnuRVn%2FPyHWXY3e0hgvG9wqjQsA%2BPqd%0ABj8n6A%3D%3D|预订|2400000Z490G|Z49|BXP|CDW|BXP|CDW|11:28|08:39|21:11|N|wnegli2OAQSv7MJDP33j7d9VoVzJrjzhoJZ7uqqAY8AsR5gGv%2BBEJvjt4JE%3D|20190201|3|P4|01|09|0|0||||无|||无||无|无|||||10401030|1413|0|1|\",\n" +
                "      \"|预订|240000K1171L|K117|BXP|PRW|BXP|CDW|11:36|17:03|29:27|N|49IVAswsVdDHP9c3izjLyycJGdTVxid9Wrzkct0YSzOWanJnKR1NESQFFEc%3D|20190201|3|PB|01|23|0|0||||无|||无||无|无|||||10401030|1413|0|0|null\",\n" +
                "      \"8qR4cmKE279sl402V0WSVaYEa69OxE6osXIRhbP7rGgy8AQfF6tdr6Tj31f%2B%2F3dVow08p1jdQizj%0A52z1pWzvmYr8%2BFuGFFhJbs0z76DwGaswinCWhOu4zAavH4E38fBoTKP327vr8XoMNba%2FI2o%2FSD7o%0AO4woABo3b6RPp89KMIRJwSx8cxK3vYKUlKYL9J0W4JlIQkT0eaSxnmmZyHe3%2FsglYZgOU86Uy3%2FT%0A0Tb7vS2j5KaT%2FiNpfOKwv3xh20d78%2B%2BcaXntbpaOBk3npWy9GXBY1B9tzZNC9Qgt3Kizxm96p9Hn%0A|预订|240000G34900|G349|BXP|ICW|BXP|ICW|15:13|22:58|07:45|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P4|01|05|1|0|||||||||||无|无|无||O0M090|OM9|0|1|OM\",\n" +
                "      \"Hjdc1xEoVM5zAodhYxAhzL6wEr6xhYAZiSt8dYj6OcxCE6L3TKOVLh%2F8lYPO6ZYUbQveka%2F8ygKD%0ADgFPj3IwOFR2gMtUo6831cP9i4EjK14IFZzsdOz%2FCguhHfya%2Ft8rMLyJgYKPEsxsMHx%2Fy4K9snGr%0A3qs5uPQIrMvF%2BdQp6XzzTYQSVwnSFhm7CUp%2F3TSawFf09UR8Rfi29BMcDPJBt30AdKAvaoz49t7%2F%0AGZzOo61bmdrkEqvM4T%2B2wTlEvNjn3NJWOOZ5IEkkYkpkv91PsZJmu%2Foh%2FbcA6b8VMXLni0TDKdgh%0A8Eai%2BA%3D%3D|预订|24000000T714|T7|BXP|CDW|BXP|CDW|16:40|20:36|27:56|N|cQhfAWLqHQFYyKcvYy90B%2BtyoELpIMS%2BWwd9RBCoGbU1%2BNUuKEwnwLqECTA%3D|20190201|3|P3|01|15|0|0||||无|||无||无|无|||||10401030|1413|0|1|\",\n" +
                "      \"Np50nVyPif0F74%2BbxhS45FfdEJN2ml%2FkcC%2Fbm6w2srLI%2FZXtj0UVXuqYEaG37N%2BZevmOfj2esnkJ%0ANAFUsUK8J%2BSweS6o%2BSAG%2FdnQl%2Bp80f44BBwH8Cezm6gdBJMdADALgxbRBP21k%2FekGb0emLmyJVJT%0AwERsv8EIYlcUpbxCQ%2BKDOg8bZ5riX4%2Fdv%2BKTVE2xX54wbe72vVWkg2Gbe4v%2Fn4JDluYWJHViIKgT%0AAwnZa5ZTzWZmLgtI5HIrHTq169Kh18w%2BTbSO7xlKa97nQNqJLrN1eJs5O8a0wW3YcZR7ymOK4pZ2%0ATYJtaDDcWJU%3D|预订|24000K13630S|K1363|BXP|CDW|BXP|CDW|22:06|07:37|33:31|N|2%2BHBM8M6tSlfZFbiQ19ktdV6NOCAv7wlNAn03WAG6HRjAFdSoGOKz%2FRqhTQ%3D|20190201|3|PB|01|14|0|0||||无|||无||无|无|||||10401030|1413|0|1|43\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"httpstatus\": 200,\n" +
                "  \"messages\": \"\",\n" +
                "  \"status\": true\n" +
                "}";
    }

    @RequestMapping(value = "json2",method = {RequestMethod.POST,RequestMethod.GET})
    public String jsonTwo(){
        return "{\n" +
                "  \"resultStatus\": 1000,\n" +
                "  \"result\": {\n" +
                "    \"error_msg\": \"\",\n" +
                "    \"fallbackOperationType\": \"\",\n" +
                "    \"succ_flag\": \"1\",\n" +
                "    \"ticketResult\": [\n" +
                "      {\n" +
                "        \"arrive_time\": \"04:48\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京\",\n" +
                "        \"from_station_telecode\": \"BJP\",\n" +
                "        \"houbu_seat_limit\": \"43\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"28:00\",\n" +
                "        \"location_code\": \"PC#MjAxOTAxMzEjMDAjMzYwMyMyODowMCMwMDo0OCMyNDAwMDAzNjAzMDQjQkpQI0NVVyMwNDo0OCPljJfkuqwj6YeN5bqG5YyXIzAxIzExI1IybWZlYmJVRVJuYlJwa1ZsVXcxVEI1OHB0RElENC80Rk55b0VMRnJiUjBVbTA1MjloNDlPZ1RUTTJNPSNQQyMwIzI5IzEwMDAjNCMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM5NDQwMDAwMCMyMDE5MDEzMSNCSlAjQ1VXIzEjNDBERTlFQUMwNjI1MzQwMTAxODU1NTA3MjBDRjYzNEEzRkZCNTU4QkMwMTU5ODI1QkE5MTBGNEY=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BJP\",\n" +
                "        \"start_time\": \"00:48\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"3603\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"240000360304\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"R2mfebbUERnbRpkVlUw1TB58ptDID4/4FNyoELFrbR0Um0529h49OgTTM2M=\",\n" +
                "        \"yp_info_cover\": \"1010503413403530000010105000213021000000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"09:35\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"GIW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_train_flag\": \"0\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"26:23\",\n" +
                "        \"location_code\": \"PB#MjAxOTAxMzEjMDAjSzUwNyMyNjoyMyMwNzoxMiMyNDAwMDBLNTA3MkcjQlhQI0NYVyMwOTozNSPljJfkuqzopb8j6YeN5bqG6KW/IzAxIzE5I3ExdmZWYmN6Y2J6bWg0bGx6R3VrL050NWF5VmJRV3pkREVHQkZGeFg2eXByaVFybjFqdXJqQ2swL2JRPSNQQiMwIzI5IzA4MDAjMiMzMSMwMzU3IzIwIzE4MDMjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjR0lXIzAjQUIwRDJBQzg3NUIzREJDRDFGNDIyNjMwRkEzQkJBNDYxNTRBMkQyMTc5MjQ4QUQyNkMzQ0ZBRDU=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"07:12\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"K507\",\n" +
                "        \"to_station_name\": \"重庆西\",\n" +
                "        \"to_station_telecode\": \"CXW\",\n" +
                "        \"train_no\": \"240000K5072G\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"q1vfVbczcbzmh4llzGuk/Nt5ayVbQWzdDEGBFFxX6ypriQrn1jurjCk0/bQ=\",\n" +
                "        \"yp_info_cover\": \"1021703184405850000010217000003037000000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"20:29\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"0\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"OM9\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"1\",\n" +
                "        \"lishi\": \"12:06\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjRzMwOSMxMjowNiMwODoyMyMyNDAwMDBHMzA5MEUjQlhQI0NVVyMyMDoyOSPljJfkuqzopb8j6YeN5bqG5YyXIzAxIzE2I2NPaEtJSDdVSUJ0K1JwdHZ4ZWxkUW5qWWJ6SmR1UXlLeml3Q0lTRElOOElKRjE1OSNQNCMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1VXIzEjMzdBMERGMzE0ODgyQTZBN0IyMjU2RDc2MDk5ODBCMDQyRjJCNEUxMDQwODhGQjQ5NTNFNUY0QTI=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"08:23\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"G309\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"240000G3090E\",\n" +
                "        \"yp_ex\": \"O0M090\",\n" +
                "        \"yp_info\": \"cOhKIH7UIBt+RptvxeldQnjYbzJduQyKziwCISDIN8IJF159\",\n" +
                "        \"yp_info_cover\": \"O079200000M1267000009245700000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"20:48\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"0\",\n" +
                "        \"end_station_telecode\": \"CXW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"OM9\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"1\",\n" +
                "        \"lishi\": \"11:26\",\n" +
                "        \"location_code\": \"P2#MjAxOTAxMzEjMDAjRzU3MSMxMToyNiMwOToyMiMyNDAwMDBHNTcxMFMjQlhQI0NYVyMyMDo0OCPljJfkuqzopb8j6YeN5bqG6KW/IzAxIzIwI2RWR0lFRmdyTkpQS0pwcFQzOXk0RmtFTTdveGFsRXlCSEQzd2t0NEhlTGg0ell5eiNQMiMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1hXIzEjNTU4QkEzODcyOUFBNTZCNzdCMkE2QzNBMjc2NUE1MEY5MEZCNDg2NTZBRkNCNzFCMTVDNTg3NTk=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"09:22\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"G571\",\n" +
                "        \"to_station_name\": \"重庆西\",\n" +
                "        \"to_station_telecode\": \"CXW\",\n" +
                "        \"train_no\": \"240000G5710S\",\n" +
                "        \"yp_ex\": \"O0M090\",\n" +
                "        \"yp_info\": \"dVGIEFgrNJPKJppT39y4FkEM7oxalEyBHD3wkt4HeLh4zYyz\",\n" +
                "        \"yp_info_cover\": \"O092450000M1479500009285500000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"17:05\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CQW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"30:35\",\n" +
                "        \"location_code\": \"PB#MjAxOTAxMzEjMDAjSzU4OSMzMDozNSMxMDozMCMyNDAwMDBLNTg5MTYjQlhQI0NRVyMxNzowNSPljJfkuqzopb8j6YeN5bqGIzAxIzI1I1BOUFRyLzdzNWd4QUpLVzkwbTlrUnNuU240M3c4UzFZZk9mdzVlT3pyUWttbnUyY29pMXRlODVrL0hFPSNQQiMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1FXIzEjOTk4NTM2REI3Mjg4MjY1N0Q0ODMwMUI5RDc0NkI0MTc4OTdGMzBBNTUwOThGRTAwMDBCNzE2OTc=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"10:30\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"K589\",\n" +
                "        \"to_station_name\": \"重庆\",\n" +
                "        \"to_station_telecode\": \"CQW\",\n" +
                "        \"train_no\": \"240000K58916\",\n" +
                "        \"yp_ex\": \"10403010\",\n" +
                "        \"yp_info\": \"PNPTr/7s5gxAJKW90m9kRsnSn43w8S1YfOfw5eOzrQkmnu2coi1te85k/HE=\",\n" +
                "        \"yp_info_cover\": \"1023603093406290000030399000001023600000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"05:56\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CDW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_train_flag\": \"0\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"18:28\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjWjQ5IzE4OjI4IzExOjI4IzI0MDAwMDBaNDkwRyNCWFAjQ1VXIzA1OjU2I+WMl+S6rOilvyPph43luobljJcjMDEjMDgjNWUwQ2hhSjNna2VVWTFzajRWODFLZi9nNUV5VGxlbVFraUM5SEdoYnQ1M1h2ZmRBcmRxVUt6dkZyb2c9I1A0IzAjMjkjMDgwMCMyIzMxIzAzNTcjMTkjMTcwNyMwIzE1NDc0NTU2MTYzNTcjMTU0NzQ1NjIwNDc5NCMxNTQ2Mzg3MjAwMDAwIzIwMTkwMTMxI0JYUCNDRFcjMCNDMzM0QkJDOTZCRDBFMjI4NTUwMDc2MjY2MkJEMUYzQjlBMzM3OUEzM0Y4MzU4QUU0NkQxMTE5NA==\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"11:28\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"Z49\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"2400000Z490G\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"5e0ChaJ3gkeUY1sj4V81Kf/g5EyTlemQkiC9HGhbt53XvfdArdqUKzvFrog=\",\n" +
                "        \"yp_info_cover\": \"1022903000406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"16:14\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"25:09\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjVDkjMjU6MDkjMTU6MDUjMjQwMDAwMDBUOTBZI0JYUCNDVVcjMTY6MTQj5YyX5Lqs6KW/I+mHjeW6huWMlyMwMSMxNCM1ZTBDaGFKM2drZVVZMXNqNFY4MUtmL2c1RXlUbGVtUWtpQzlIR2hidDUzWHZmZEFyZHFVS3p2RnJvZz0jUDQjMCMyOSMwODAwIzIjMzEjMDM1NyMzNCMxNzE3IzAjMTU0NzQ1NTYxNjM1NyMxNTQ3NDU2MjA0Nzk0IzE1NDYzODcyMDAwMDAjMjAxOTAxMzEjQlhQI0NVVyMxI0NCMzA4RjU2MDkyRkVGM0E2RDY1RTJFNjgyMEUwNTRFMjg4OEU3QTY3MTgzNjkzQTJCMjY4QjhG\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"15:05\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"T9\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"24000000T90Y\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"5e0ChaJ3gkeUY1sj4V81Kf/g5EyTlemQkiC9HGhbt53XvfdArdqUKzvFrog=\",\n" +
                "        \"yp_info_cover\": \"1022903000406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"11:56\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"18:08\",\n" +
                "        \"location_code\": \"P3#MjAxOTAxMzEjMDAjWjMjMTg6MDgjMTc6NDgjMjQwMDAwMDBaMzBMI0JYUCNDVVcjMTE6NTYj5YyX5Lqs6KW/I+mHjeW6huWMlyMwMSMwNyM1ZTBDaGFKM2drZVVZMXNqNFY4MUtmL2c1RXlUbGVtUWtpQzlIR2hidDUzWHZmZEFyZHFVS3p2RnJvZz0jUDMjMCMyOSMwODAwIzIjMzEjMDM1NyMzNCMxNzE3IzAjMTU0NzQ1NTYxNjM1OCMxNTQ3NDU2MjA0Nzk0IzE1NDYzODcyMDAwMDAjMjAxOTAxMzEjQlhQI0NVVyMxIzAxOThENEYxOURBRUFCNkEyNzQwRTlCODI4MjI0MTA2QjNENUEwRDRGMzUyQzU5NTBCQ0I1ODlF\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"17:48\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"Z3\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"24000000Z30L\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"5e0ChaJ3gkeUY1sj4V81Kf/g5EyTlemQkiC9HGhbt53XvfdArdqUKzvFrog=\",\n" +
                "        \"yp_info_cover\": \"1022903000406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"17:01\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"22:55\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjWjk1IzIyOjU1IzE4OjA2IzI0MDAwMDBaOTUwNiNCWFAjQ1VXIzE3OjAxI+WMl+S6rOilvyPph43luobljJcjMDEjMTEjSVRWOGJXNmhOZkRObE95T1JxSnRyU3RxZUNmcGdXSVNWSEM5Zll5eDRNVjVWRTQrUWVVd1FEZUxrcjQ9I1A0IzAjMjkjMDgwMCMyIzMxIzAzNTcjMzQjMTcxNyMwIzE1NDc0NTU2MTYzNTgjMTU0NzQ1NjIwNDc5NCMxNTQ2Mzg3MjAwMDAwIzIwMTkwMTMxI0JYUCNDVVcjMSM1Mjc2QUUyRDMwOEU2NDhGOEE5RjAxMEEwRDVGMEVDRTJEQTI0ODM1RTAzMkQzMjhFNDJBNTNBRQ==\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"18:06\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"Z95\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"2400000Z9506\",\n" +
                "        \"yp_ex\": \"10403010\",\n" +
                "        \"yp_info\": \"ITV8bW6hNfDNlOyORqJtrStqeCfpgWISVHC9fYyx4MV5VE4+QeUwQDeLkr4=\",\n" +
                "        \"yp_info_cover\": \"1024303000406540000030414000001024300000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"23:40\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"26:17\",\n" +
                "        \"location_code\": \"PB#MjAxOTAxMzEjMDAjSzgxOSMyNjoxNyMyMToyMyMyNDAwMDBLODE5MFAjQlhQI0NVVyMyMzo0MCPljJfkuqzopb8j6YeN5bqG5YyXIzAxIzIwI1BMZG5DVEMvcjRKVzhUZmRtUUVqWTdWNXpUcXg5VDJKTHA0QS9HQXZLUWRoTzdnSjM2aGM1TFNqR3pvPSNQQiMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU4IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1VXIzEjQTRCQjhDNzkzMTEzNTA0QkY5RjQwQUExNjY1NjI3MkQwOTdCRjZCNkY4NkMzM0E1MUU4RjlBNkY=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"21:23\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"K819\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"240000K8190P\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"PLdnCTC/r4JW8TfdmQEjY7V5zTqx9T2JLp4A/GAvKQdhO7gJ36hc5LSjGzo=\",\n" +
                "        \"yp_info_cover\": \"1022903099406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
    @RequestMapping(value = "json3",method = {RequestMethod.POST,RequestMethod.GET})
    public String jsonThree(){
        return "{\n" +
                "  \"data\": {\n" +
                "    \"flag\": \"1\",\n" +
                "    \"map\": {\n" +
                "      \"BXP\": \"北京西\",\n" +
                "      \"CDW\": \"成都\",\n" +
                "      \"ICW\": \"成都东\"\n" +
                "    },\n" +
                "    \"result\": [\n" +
                "      \"zisjPgJIdkTFDtXX6D%2Fdh7ApeEggyOHn1QRS5nNIvwSjTLJRLRHaaKGxeYiwKBZT4D3WimDL4w2L%0ADnWDAoF3vDtztf0TQ9b6yEVw%2BKQwgm3SGDIsckiSzWlCEwY%2FDRt8Z%2BCUf7AlOG%2BAi5CCktrkjTGp%0AbqhYuDBW3WXHoRni2595VyB7adRf7e5Kx7A1PnWQu9NL%2BKcakaeFTs80ssEKPyWZur%2BpBliIErYt%0A2Mj0YAGuYVJOYiJyy%2BNmMahu7zTMiHiLXHxLXoH7ZF%2BNEaoUiHULgaYvSkOoYDHoicJvYYXqA%2BdV%0AhVu4mPUaliA%3D|预订|24000K42170B|K4217|BXP|CDW|BXP|CDW|01:56|21:57|44:01|Y|6UhLOzAiN5RdBIZjXUpHQ01%2Bo9vGK6MYF0BiUktoBZBOglEh37muuDJekaE%3D|20190201|3|PC|01|13|0|0||||无|||有||无|有|||||10401030|1413|1|1|3\",\n" +
                "      \"EIgby6Hg69T2TZUGLyoVybegYtTWI9q%2FteKpndoRLCXIBZoJ47eS0MKiYMtTsfrT8e7dvtoCMI1V%0ADxRy%2F1J%2FD0ZeSXGqY%2FlO%2BtlZAqWcqUYaLcwb2QN85PQ9KYJrLHN7BpSk1ijHnHeKjfIMhDHhpDZ%2F%0Ay9OWqY%2BXEIFwfiqhpHbuQ22ZreypfI2ykrX3bUYZwc93q3IesQMsy7yg5vihu30PJKUf6AVArSqf%0AScESjQ%2FIAHQSq79dzVchQAfHuqaVz4q6IH1I2gak904wqF%2BIt5mmpVryyAAw%2BWuXkT7uWOk%3D|预订|2400000G890C|G89|BXP|ICW|BXP|ICW|06:53|14:38|07:45|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P4|01|05|1|0|||||||||||无|无|无||O0M090|OM9|0|1|OM9\",\n" +
                "      \"VNbWr0i9RmqbmipyJHlUrmBrcLaIxPHzOJMDo1eAPs9hH79Y2jYtKeFpuxEIzhJ9CA7FvHYespaN%0A1VpHeqypjwRDgG5cpibNbHdbZ5atT0u8LV7pwaj1R4Cndnyby0Nolyx4ZIhsqDFAOK5I2x0pxd2%2B%0AG5iO8zvPjFDn1CVBdGzouOrTLhRa%2F%2FPVB5Y4b6VF%2FYGzbvEqE0zmHYtcTsD4I6%2Fey9xMoMFBe9WC%0AKv6f1pLZ62xjEVpeXdecWqFHJFIn74gnDkD8H7tJn6%2FwAW2Ba4nt7aYCcGwOyl31YZgtTbQ5tKHs%0AXlLeBg%3D%3D|预订|240000K8170X|K817|BXP|CDW|BXP|CDW|08:01|12:41|28:40|Y|%2FS%2BBzpJ9OKzUA7GGzKmnJPVnsSalRHDN7iW8kxNAclJbXogbvI7d8YcFP98%3D|20190201|3|PB|01|23|0|0||||无|||有||无|无|||||10401030|1413|0|1|413\",\n" +
                "      \"a%2Bt0J%2FKmu2WW7XRGp2gt2x6ndSw7oQk3QKvoGltGLR3oo2JpWsSFrE1fBmQFFGJT1CYwvdFYzn%2BC%0AFB4O%2FKpK%2FmfLefYJMun5gL54RBlk%2FxZ4vK5seV3WzkGpMRQgZp940X4djS3o7iGS3Lb73Pp4kWq%2F%0A0Jz7BkhUrtWR6ue91A%2BnA93tLuHLW09ZZwbPVoJycE2GQDo7S%2FFTmKMqc6TaKwv983I7c7nlstEc%0AeKY8qPZxyPy9FozSFBRSOAT8nunEHcSdWtmiJm4PZLynGpSKSHvoWheAMZi%2BvDwl8b0Wab1jI6w6%0A|预订|240000G5710S|G571|BXP|CXW|BXP|ICW|09:22|19:08|09:46|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P2|01|17|1|0|||||||||||无|无|无||O0M090|OM9|0|1|\",\n" +
                "      \"C6glElDHanDE7oTtc8KRIo1z%2Fv8sr9J85qgWyy8EKbPiiIYQoimkpNoqEaL6jYNJf1T2Obs1b4QM%0Al0OwUdZSlSDAGZqo%2Fh4Bxx0nqBJ7E2QZYCbkZEirqDheU%2Fp2YBxfv0TG0hq4NYNctw89jZhOKaMP%0AdtzJIiuDY%2FGr%2Fbd7pamfvDv6zqMGAMpYYZwVRLO0zg%2FhsxWW9k3xpJSCK2iBkrKKlofnuzi2sjDa%0AHy1C%2FW5IYoJ4rTvO0gP0CIQkBWJydJeNSkeroJyR1GxylklNGorFqsoPnUCG%2BiIhCYMLxBdncwLe%0A|预订|240000G3070J|G307|BXP|ICW|BXP|ICW|09:38|19:36|09:58|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P3|01|19|1|0|||||||||||无|无|无||O0M090|OM9|0|1|OM9\",\n" +
                "      \"yOTBqKCnboxFJ1TKR35S8vFEVzaKfUZKjSubwhbvsd542SICav9oS3T5jjgoNs5xzNQRr0qi8Vuv%0AYQ0kaTtGMEL9PMD3vl%2BUzjF6tGZuZvNogXJB5JXG%2F1MlGB%2FFaUquQ7dAIEsxrWQDF4KU7gj2Pa0p%0AgVz15rCvMsZRN5TF8QEVj24XAh%2BM4vH07JIXfaAMBdXNwfEcM7lqEa4EBTmDPT8gU35U%2FWZjkUoq%0Akm%2BLyF4ckdXqY6tdXTDdjLSc6Yxexm1K%2B7IVjFvFH4snFGnuRVn%2FPyHWXY3e0hgvG9wqjQsA%2BPqd%0ABj8n6A%3D%3D|预订|2400000Z490G|Z49|BXP|CDW|BXP|CDW|11:28|08:39|21:11|N|wnegli2OAQSv7MJDP33j7d9VoVzJrjzhoJZ7uqqAY8AsR5gGv%2BBEJvjt4JE%3D|20190201|3|P4|01|09|0|0||||无|||无||无|无|||||10401030|1413|0|1|\",\n" +
                "      \"|预订|240000K1171L|K117|BXP|PRW|BXP|CDW|11:36|17:03|29:27|N|49IVAswsVdDHP9c3izjLyycJGdTVxid9Wrzkct0YSzOWanJnKR1NESQFFEc%3D|20190201|3|PB|01|23|0|0||||无|||无||无|无|||||10401030|1413|0|0|null\",\n" +
                "      \"8qR4cmKE279sl402V0WSVaYEa69OxE6osXIRhbP7rGgy8AQfF6tdr6Tj31f%2B%2F3dVow08p1jdQizj%0A52z1pWzvmYr8%2BFuGFFhJbs0z76DwGaswinCWhOu4zAavH4E38fBoTKP327vr8XoMNba%2FI2o%2FSD7o%0AO4woABo3b6RPp89KMIRJwSx8cxK3vYKUlKYL9J0W4JlIQkT0eaSxnmmZyHe3%2FsglYZgOU86Uy3%2FT%0A0Tb7vS2j5KaT%2FiNpfOKwv3xh20d78%2B%2BcaXntbpaOBk3npWy9GXBY1B9tzZNC9Qgt3Kizxm96p9Hn%0A|预订|240000G34900|G349|BXP|ICW|BXP|ICW|15:13|22:58|07:45|N|tHVLmNkx6rLI4aJ8TAFEyue%2BRCbo2HYmcShDSHamMKzAe102|20190201|3|P4|01|05|1|0|||||||||||无|无|无||O0M090|OM9|0|1|OM\",\n" +
                "      \"Hjdc1xEoVM5zAodhYxAhzL6wEr6xhYAZiSt8dYj6OcxCE6L3TKOVLh%2F8lYPO6ZYUbQveka%2F8ygKD%0ADgFPj3IwOFR2gMtUo6831cP9i4EjK14IFZzsdOz%2FCguhHfya%2Ft8rMLyJgYKPEsxsMHx%2Fy4K9snGr%0A3qs5uPQIrMvF%2BdQp6XzzTYQSVwnSFhm7CUp%2F3TSawFf09UR8Rfi29BMcDPJBt30AdKAvaoz49t7%2F%0AGZzOo61bmdrkEqvM4T%2B2wTlEvNjn3NJWOOZ5IEkkYkpkv91PsZJmu%2Foh%2FbcA6b8VMXLni0TDKdgh%0A8Eai%2BA%3D%3D|预订|24000000T714|T7|BXP|CDW|BXP|CDW|16:40|20:36|27:56|N|cQhfAWLqHQFYyKcvYy90B%2BtyoELpIMS%2BWwd9RBCoGbU1%2BNUuKEwnwLqECTA%3D|20190201|3|P3|01|15|0|0||||无|||无||无|无|||||10401030|1413|0|1|\",\n" +
                "      \"Np50nVyPif0F74%2BbxhS45FfdEJN2ml%2FkcC%2Fbm6w2srLI%2FZXtj0UVXuqYEaG37N%2BZevmOfj2esnkJ%0ANAFUsUK8J%2BSweS6o%2BSAG%2FdnQl%2Bp80f44BBwH8Cezm6gdBJMdADALgxbRBP21k%2FekGb0emLmyJVJT%0AwERsv8EIYlcUpbxCQ%2BKDOg8bZ5riX4%2Fdv%2BKTVE2xX54wbe72vVWkg2Gbe4v%2Fn4JDluYWJHViIKgT%0AAwnZa5ZTzWZmLgtI5HIrHTq169Kh18w%2BTbSO7xlKa97nQNqJLrN1eJs5O8a0wW3YcZR7ymOK4pZ2%0ATYJtaDDcWJU%3D|预订|24000K13630S|K1363|BXP|CDW|BXP|CDW|22:06|07:37|33:31|N|2%2BHBM8M6tSlfZFbiQ19ktdV6NOCAv7wlNAn03WAG6HRjAFdSoGOKz%2FRqhTQ%3D|20190201|3|PB|01|14|0|0||||无|||无||无|无|||||10401030|1413|0|1|43\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"httpstatus\": 302,\n" +
                "  \"messages\": \"\",\n" +
                "  \"status\": true\n" +
                "}";
    }
    @RequestMapping(value = "json4",method = {RequestMethod.POST,RequestMethod.GET})
    public String jsonFour(){
        return "{\n" +
                "  \"resultStatus\": 43002,\n" +
                "  \"result\": {\n" +
                "    \"error_msg\": \"\",\n" +
                "    \"fallbackOperationType\": \"\",\n" +
                "    \"succ_flag\": \"1\",\n" +
                "    \"ticketResult\": [\n" +
                "      {\n" +
                "        \"arrive_time\": \"04:48\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京\",\n" +
                "        \"from_station_telecode\": \"BJP\",\n" +
                "        \"houbu_seat_limit\": \"43\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"28:00\",\n" +
                "        \"location_code\": \"PC#MjAxOTAxMzEjMDAjMzYwMyMyODowMCMwMDo0OCMyNDAwMDAzNjAzMDQjQkpQI0NVVyMwNDo0OCPljJfkuqwj6YeN5bqG5YyXIzAxIzExI1IybWZlYmJVRVJuYlJwa1ZsVXcxVEI1OHB0RElENC80Rk55b0VMRnJiUjBVbTA1MjloNDlPZ1RUTTJNPSNQQyMwIzI5IzEwMDAjNCMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM5NDQwMDAwMCMyMDE5MDEzMSNCSlAjQ1VXIzEjNDBERTlFQUMwNjI1MzQwMTAxODU1NTA3MjBDRjYzNEEzRkZCNTU4QkMwMTU5ODI1QkE5MTBGNEY=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BJP\",\n" +
                "        \"start_time\": \"00:48\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"3603\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"240000360304\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"R2mfebbUERnbRpkVlUw1TB58ptDID4/4FNyoELFrbR0Um0529h49OgTTM2M=\",\n" +
                "        \"yp_info_cover\": \"1010503413403530000010105000213021000000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"09:35\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"GIW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_train_flag\": \"0\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"26:23\",\n" +
                "        \"location_code\": \"PB#MjAxOTAxMzEjMDAjSzUwNyMyNjoyMyMwNzoxMiMyNDAwMDBLNTA3MkcjQlhQI0NYVyMwOTozNSPljJfkuqzopb8j6YeN5bqG6KW/IzAxIzE5I3ExdmZWYmN6Y2J6bWg0bGx6R3VrL050NWF5VmJRV3pkREVHQkZGeFg2eXByaVFybjFqdXJqQ2swL2JRPSNQQiMwIzI5IzA4MDAjMiMzMSMwMzU3IzIwIzE4MDMjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjR0lXIzAjQUIwRDJBQzg3NUIzREJDRDFGNDIyNjMwRkEzQkJBNDYxNTRBMkQyMTc5MjQ4QUQyNkMzQ0ZBRDU=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"07:12\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"K507\",\n" +
                "        \"to_station_name\": \"重庆西\",\n" +
                "        \"to_station_telecode\": \"CXW\",\n" +
                "        \"train_no\": \"240000K5072G\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"q1vfVbczcbzmh4llzGuk/Nt5ayVbQWzdDEGBFFxX6ypriQrn1jurjCk0/bQ=\",\n" +
                "        \"yp_info_cover\": \"1021703184405850000010217000003037000000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"20:29\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"0\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"OM9\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"1\",\n" +
                "        \"lishi\": \"12:06\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjRzMwOSMxMjowNiMwODoyMyMyNDAwMDBHMzA5MEUjQlhQI0NVVyMyMDoyOSPljJfkuqzopb8j6YeN5bqG5YyXIzAxIzE2I2NPaEtJSDdVSUJ0K1JwdHZ4ZWxkUW5qWWJ6SmR1UXlLeml3Q0lTRElOOElKRjE1OSNQNCMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1VXIzEjMzdBMERGMzE0ODgyQTZBN0IyMjU2RDc2MDk5ODBCMDQyRjJCNEUxMDQwODhGQjQ5NTNFNUY0QTI=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"08:23\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"G309\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"240000G3090E\",\n" +
                "        \"yp_ex\": \"O0M090\",\n" +
                "        \"yp_info\": \"cOhKIH7UIBt+RptvxeldQnjYbzJduQyKziwCISDIN8IJF159\",\n" +
                "        \"yp_info_cover\": \"O079200000M1267000009245700000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"20:48\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"0\",\n" +
                "        \"end_station_telecode\": \"CXW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"OM9\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"1\",\n" +
                "        \"lishi\": \"11:26\",\n" +
                "        \"location_code\": \"P2#MjAxOTAxMzEjMDAjRzU3MSMxMToyNiMwOToyMiMyNDAwMDBHNTcxMFMjQlhQI0NYVyMyMDo0OCPljJfkuqzopb8j6YeN5bqG6KW/IzAxIzIwI2RWR0lFRmdyTkpQS0pwcFQzOXk0RmtFTTdveGFsRXlCSEQzd2t0NEhlTGg0ell5eiNQMiMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1hXIzEjNTU4QkEzODcyOUFBNTZCNzdCMkE2QzNBMjc2NUE1MEY5MEZCNDg2NTZBRkNCNzFCMTVDNTg3NTk=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"09:22\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"G571\",\n" +
                "        \"to_station_name\": \"重庆西\",\n" +
                "        \"to_station_telecode\": \"CXW\",\n" +
                "        \"train_no\": \"240000G5710S\",\n" +
                "        \"yp_ex\": \"O0M090\",\n" +
                "        \"yp_info\": \"dVGIEFgrNJPKJppT39y4FkEM7oxalEyBHD3wkt4HeLh4zYyz\",\n" +
                "        \"yp_info_cover\": \"O092450000M1479500009285500000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"17:05\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CQW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"30:35\",\n" +
                "        \"location_code\": \"PB#MjAxOTAxMzEjMDAjSzU4OSMzMDozNSMxMDozMCMyNDAwMDBLNTg5MTYjQlhQI0NRVyMxNzowNSPljJfkuqzopb8j6YeN5bqGIzAxIzI1I1BOUFRyLzdzNWd4QUpLVzkwbTlrUnNuU240M3c4UzFZZk9mdzVlT3pyUWttbnUyY29pMXRlODVrL0hFPSNQQiMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU3IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1FXIzEjOTk4NTM2REI3Mjg4MjY1N0Q0ODMwMUI5RDc0NkI0MTc4OTdGMzBBNTUwOThGRTAwMDBCNzE2OTc=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"10:30\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"K589\",\n" +
                "        \"to_station_name\": \"重庆\",\n" +
                "        \"to_station_telecode\": \"CQW\",\n" +
                "        \"train_no\": \"240000K58916\",\n" +
                "        \"yp_ex\": \"10403010\",\n" +
                "        \"yp_info\": \"PNPTr/7s5gxAJKW90m9kRsnSn43w8S1YfOfw5eOzrQkmnu2coi1te85k/HE=\",\n" +
                "        \"yp_info_cover\": \"1023603093406290000030399000001023600000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"05:56\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CDW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_train_flag\": \"0\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"18:28\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjWjQ5IzE4OjI4IzExOjI4IzI0MDAwMDBaNDkwRyNCWFAjQ1VXIzA1OjU2I+WMl+S6rOilvyPph43luobljJcjMDEjMDgjNWUwQ2hhSjNna2VVWTFzajRWODFLZi9nNUV5VGxlbVFraUM5SEdoYnQ1M1h2ZmRBcmRxVUt6dkZyb2c9I1A0IzAjMjkjMDgwMCMyIzMxIzAzNTcjMTkjMTcwNyMwIzE1NDc0NTU2MTYzNTcjMTU0NzQ1NjIwNDc5NCMxNTQ2Mzg3MjAwMDAwIzIwMTkwMTMxI0JYUCNDRFcjMCNDMzM0QkJDOTZCRDBFMjI4NTUwMDc2MjY2MkJEMUYzQjlBMzM3OUEzM0Y4MzU4QUU0NkQxMTE5NA==\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"11:28\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"Z49\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"2400000Z490G\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"5e0ChaJ3gkeUY1sj4V81Kf/g5EyTlemQkiC9HGhbt53XvfdArdqUKzvFrog=\",\n" +
                "        \"yp_info_cover\": \"1022903000406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"16:14\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"25:09\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjVDkjMjU6MDkjMTU6MDUjMjQwMDAwMDBUOTBZI0JYUCNDVVcjMTY6MTQj5YyX5Lqs6KW/I+mHjeW6huWMlyMwMSMxNCM1ZTBDaGFKM2drZVVZMXNqNFY4MUtmL2c1RXlUbGVtUWtpQzlIR2hidDUzWHZmZEFyZHFVS3p2RnJvZz0jUDQjMCMyOSMwODAwIzIjMzEjMDM1NyMzNCMxNzE3IzAjMTU0NzQ1NTYxNjM1NyMxNTQ3NDU2MjA0Nzk0IzE1NDYzODcyMDAwMDAjMjAxOTAxMzEjQlhQI0NVVyMxI0NCMzA4RjU2MDkyRkVGM0E2RDY1RTJFNjgyMEUwNTRFMjg4OEU3QTY3MTgzNjkzQTJCMjY4QjhG\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"15:05\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"T9\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"24000000T90Y\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"5e0ChaJ3gkeUY1sj4V81Kf/g5EyTlemQkiC9HGhbt53XvfdArdqUKzvFrog=\",\n" +
                "        \"yp_info_cover\": \"1022903000406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"11:56\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"18:08\",\n" +
                "        \"location_code\": \"P3#MjAxOTAxMzEjMDAjWjMjMTg6MDgjMTc6NDgjMjQwMDAwMDBaMzBMI0JYUCNDVVcjMTE6NTYj5YyX5Lqs6KW/I+mHjeW6huWMlyMwMSMwNyM1ZTBDaGFKM2drZVVZMXNqNFY4MUtmL2c1RXlUbGVtUWtpQzlIR2hidDUzWHZmZEFyZHFVS3p2RnJvZz0jUDMjMCMyOSMwODAwIzIjMzEjMDM1NyMzNCMxNzE3IzAjMTU0NzQ1NTYxNjM1OCMxNTQ3NDU2MjA0Nzk0IzE1NDYzODcyMDAwMDAjMjAxOTAxMzEjQlhQI0NVVyMxIzAxOThENEYxOURBRUFCNkEyNzQwRTlCODI4MjI0MTA2QjNENUEwRDRGMzUyQzU5NTBCQ0I1ODlF\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"17:48\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"Z3\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"24000000Z30L\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"5e0ChaJ3gkeUY1sj4V81Kf/g5EyTlemQkiC9HGhbt53XvfdArdqUKzvFrog=\",\n" +
                "        \"yp_info_cover\": \"1022903000406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"17:01\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"22:55\",\n" +
                "        \"location_code\": \"P4#MjAxOTAxMzEjMDAjWjk1IzIyOjU1IzE4OjA2IzI0MDAwMDBaOTUwNiNCWFAjQ1VXIzE3OjAxI+WMl+S6rOilvyPph43luobljJcjMDEjMTEjSVRWOGJXNmhOZkRObE95T1JxSnRyU3RxZUNmcGdXSVNWSEM5Zll5eDRNVjVWRTQrUWVVd1FEZUxrcjQ9I1A0IzAjMjkjMDgwMCMyIzMxIzAzNTcjMzQjMTcxNyMwIzE1NDc0NTU2MTYzNTgjMTU0NzQ1NjIwNDc5NCMxNTQ2Mzg3MjAwMDAwIzIwMTkwMTMxI0JYUCNDVVcjMSM1Mjc2QUUyRDMwOEU2NDhGOEE5RjAxMEEwRDVGMEVDRTJEQTI0ODM1RTAzMkQzMjhFNDJBNTNBRQ==\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"18:06\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"Z95\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"2400000Z9506\",\n" +
                "        \"yp_ex\": \"10403010\",\n" +
                "        \"yp_info\": \"ITV8bW6hNfDNlOyORqJtrStqeCfpgWISVHC9fYyx4MV5VE4+QeUwQDeLkr4=\",\n" +
                "        \"yp_info_cover\": \"1024303000406540000030414000001024300000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"arrive_time\": \"23:40\",\n" +
                "        \"controlled_train_flag\": \"0\",\n" +
                "        \"controlled_train_message\": \"正常车次，不受控\",\n" +
                "        \"day_difference\": \"1\",\n" +
                "        \"end_station_telecode\": \"CUW\",\n" +
                "        \"exchange_train_flag\": \"0\",\n" +
                "        \"from_station_name\": \"北京西\",\n" +
                "        \"from_station_telecode\": \"BXP\",\n" +
                "        \"houbu_seat_limit\": \"413\",\n" +
                "        \"houbu_train_flag\": \"1\",\n" +
                "        \"is_support_card\": \"0\",\n" +
                "        \"lishi\": \"26:17\",\n" +
                "        \"location_code\": \"PB#MjAxOTAxMzEjMDAjSzgxOSMyNjoxNyMyMToyMyMyNDAwMDBLODE5MFAjQlhQI0NVVyMyMzo0MCPljJfkuqzopb8j6YeN5bqG5YyXIzAxIzIwI1BMZG5DVEMvcjRKVzhUZmRtUUVqWTdWNXpUcXg5VDJKTHA0QS9HQXZLUWRoTzdnSjM2aGM1TFNqR3pvPSNQQiMwIzI5IzA4MDAjMiMzMSMwMzU3IzM0IzE3MTcjMCMxNTQ3NDU1NjE2MzU4IzE1NDc0NTYyMDQ3OTQjMTU0NjM4NzIwMDAwMCMyMDE5MDEzMSNCWFAjQ1VXIzEjQTRCQjhDNzkzMTEzNTA0QkY5RjQwQUExNjY1NjI3MkQwOTdCRjZCNkY4NkMzM0E1MUU4RjlBNkY=\",\n" +
                "        \"message\": \"\",\n" +
                "        \"start_station_telecode\": \"BXP\",\n" +
                "        \"start_time\": \"21:23\",\n" +
                "        \"start_train_date\": \"20190131\",\n" +
                "        \"station_train_code\": \"K819\",\n" +
                "        \"to_station_name\": \"重庆北\",\n" +
                "        \"to_station_telecode\": \"CUW\",\n" +
                "        \"train_no\": \"240000K8190P\",\n" +
                "        \"yp_ex\": \"10401030\",\n" +
                "        \"yp_info\": \"PLdnCTC/r4JW8TfdmQEjY7V5zTqx9T2JLp4A/GAvKQdhO7gJ36hc5LSjGzo=\",\n" +
                "        \"yp_info_cover\": \"1022903099406130000010229000003038900000\",\n" +
                "        \"yp_info_coverFlag\": \"1\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
