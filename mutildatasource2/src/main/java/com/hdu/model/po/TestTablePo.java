package com.hdu.model.po;

import lombok.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 2:31 PM
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestTablePo {
    private Long id;
    private String name;
    private Integer age;
    private String password;


}
