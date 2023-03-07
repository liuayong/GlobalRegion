package com.byd;

import org.junit.Test;

/**
 * @Author : LiuYingYong
 * @Date 2023/2/21
 */
public class Dimension {
    
    /*
    PeriodOutputUtils.transUnidimensional(page.getList(),
                                IndependentDemandRocVersionPageOutVo.class, "period",
                                Arrays.asList("idx", "demandNum1", "demandNum2"),
                                Arrays.asList("demandNum1:" + params.getVersionNo1(),
                                        "demandNum2:" + params.getVersionNo2(), "rocNum:波动量", "rocRate:波动率"))
     */
    @Test
    public void test() {
        String pageStr = "{\"columns\":[{\"field\":\"factNo\",\"headerName\":\"factoryCode\"},{\"field\":\"typeNo\"," +
                "\"headerName\":\"demandType\"},{\"field\":\"matNo\",\"headerName\":\"matShottext\"},{\"field\":\"matDesc\",\"headerName\":\"matShottext\"},{\"field\":\"alias\",\"headerName\":\"rowType\"},{\"children\":[{\"field\":\"datas_202301\",\"headerName\":\"0218~0228\"}],\"field\":\"0\",\"headerName\":\"M202301\"},{\"children\":[{\"field\":\"datas_202302\",\"headerName\":\"0301~0331\"}],\"field\":\"1\",\"headerName\":\"M202302\"},{\"children\":[{\"field\":\"datas_202303\",\"headerName\":\"0401~0430\"}],\"field\":\"2\",\"headerName\":\"M202303\"},{\"children\":[{\"field\":\"datas_202304\",\"headerName\":\"0501~0506\"}],\"field\":\"3\",\"headerName\":\"M202304\"}],\"list\":[{\"datas\":[{\"demandNum1\":7150,\"demandNum2\":0,\"period\":202301,\"periodEnd\":1677599999000,\"periodStart\":1676649601000,\"rocNum\":7150,\"rocRate\":\"100.00%\",\"versionNo1\":\"20230218\",\"versionNo2\":\"20230217\"},{\"demandNum1\":22544.00,\"demandNum2\":0,\"period\":202302,\"periodEnd\":1680278399000,\"periodStart\":1677600001000,\"rocNum\":22544.00,\"rocRate\":\"100.00%\",\"versionNo1\":\"20230218\",\"versionNo2\":\"20230217\"},{\"demandNum1\":19902.00,\"demandNum2\":0,\"period\":202303,\"periodEnd\":1682870399000,\"periodStart\":1680278401000,\"rocNum\":19902.00,\"rocRate\":\"100.00%\",\"versionNo1\":\"20230218\",\"versionNo2\":\"20230217\"},{\"demandNum1\":4214.00,\"demandNum2\":0,\"period\":202304,\"periodEnd\":1683388798000,\"periodStart\":1682870401000,\"rocNum\":4214.00,\"rocRate\":\"100.00%\",\"versionNo1\":\"20230218\",\"versionNo2\":\"20230217\"}],\"factNo\":\"A155\",\"idx\":0,\"matDesc\":\"HCHX-3622300_右车身控制器_M00666\",\"matNo\":\"13190379-00\",\"typeNo\":\"A200\",\"versionNo\":\"20230218\"}],\"total\":1}";
        
       
    }
}
