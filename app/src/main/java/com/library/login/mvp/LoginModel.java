package com.library.login.mvp;


import com.common.http.XRetrofit;
import com.common.http.base.BaseResponse;
import com.common.http.bean.ExampleBean;
import com.common.http.helper.RequestBodyHelper;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc:
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<BaseResponse<ExampleBean>> login(HashMap<String, Object> map) {
        return XRetrofit.getApi().testLogin(RequestBodyHelper.getRequestBody(map));
    }
}