package com.gblfy.product.utils;


import com.gblfy.product.vo.ResultVO;

/**
 * Created by gblfy
 * 2019-05-09 22:53
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
