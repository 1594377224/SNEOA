package cn.mvtech.SNEOA;

import java.util.HashMap;
import java.util.Map;

import cn.mvtech.util.G4Utils;

public class test {
	public static void main(String[] args) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qq", "9");
		paramMap.put("tenantId", "2001");
		/*long a = G4Utils.isEmpty(paramMap.get("qq")) ? 1
				: Long.parseLong((String) paramMap.get("qq"))+ 1L;
		System.out.println(a);*/
		System.out.println("------"+paramMap.toString());
		Object tenantId = paramMap.get("tenantId");
		System.out.println("++++"+tenantId);
		if(G4Utils.isEmpty(tenantId)){
			paramMap.put("tenantId", "");
		}
		System.out.println("======"+paramMap.toString());
	}
}
