package com.wj.cxfserver;

import javax.jws.WebService;

@WebService
public interface IWeatherService {

	String info(String city);
}
