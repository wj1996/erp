package com.wj.cxfserver;

public class WeatherServiceImpl implements IWeatherService{

	@Override
	public String info(String city) {
		if("北京".equals(city)) {
			return "雾霾";
		}
		return "晴天";
	}

}
