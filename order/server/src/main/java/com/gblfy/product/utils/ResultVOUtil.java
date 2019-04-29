package com.gblfy.product.utils;

import com.gblfy.product.vo.ResultVO;

/**
 * @author gblfy
 * @ClassNme ResultVOUtil
 * @Description TODO
 * @Date 2019/4/22 21:38
 * @version1.0
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
