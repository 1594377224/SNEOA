package cn.mvtech.SNEOA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mvtech.util.G4Utils;

public class test {
	public static void main(String[] args) {
		/*Map map = new HashMap();
		map.put("key1", "value1");
		map.put("key2", "value2");
		List<Map<String, String>> list1Map = new ArrayList<>();*/
		List<Map<String, String>> listMap = new ArrayList<>();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("campaignId", "10001");
		paramMap.put("provinceId", "");
		listMap.add(paramMap);
		paramMap.put("campaignId", "10003");
		paramMap.put("provinceId", "");
		listMap.add(paramMap);

		System.out.println(listMap.toString());
		
	}
}
