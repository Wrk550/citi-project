citi-gateway：网关，集中处理、转发请求
    port：7000


citi-product：产品服务，主要是和股票信息相关的接口
    展示股票产品具体信息：
        uri：product/list
        请求方式：GET
        请求参数：{"pageNO":"页码","pageSize","每页记录数"}
        示例：   {
                    "pageNO":"1",
                    "pageSize","10"
                }
        响应参数：{"items":"展示的数据列表","counts":"总记录数","page":"当前页码"}


citi-trade：交易服务，主要是和股票买入卖出相关的接口
    买入股票：
        uri：trade/buy
        请求参数：ClientName(客户姓名),Ticker(BBG代码),RIC(RIC代码),
        Size(买入数量),Price(报价器价格),Currency(证券公司货币),IssuerSector(部门)
        SalesPerson(对应销售人员),HT/PT(交易方式)
        响应参数：ClientName(客户姓名),Ticker(BBG代码),RIC(RIC代码),
        Size(买入数量),Price(报价器价格),Currency(证券公司货币),IssuerSector(部门)
        SalesPerson(对应销售人员),HT/PT(交易方式)
    卖出股票：
        uri：trade/sell
        请求参数：ClientName(客户姓名),Ticker(BBG代码),RIC(RIC代码),
        Size(卖出数量),Price(报价器价格),Currency(证券公司货币),IssuerSector(部门)
        SalesPerson(对应销售人员),HT/PT(交易方式)
        响应参数：ClientName(客户姓名),Ticker(BBG代码),RIC(RIC代码),
        Size(卖出数量),Price(报价器价格),Currency(证券公司货币),IssuerSector(部门)
        SalesPerson(对应销售人员),HT/PT(交易方式)
