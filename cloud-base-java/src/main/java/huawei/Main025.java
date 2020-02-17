package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Leijian
 * @date 2020/2/15
 */
public class Main025 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		while((s = br.readLine())!= null){
			String[] vals = s.split(" ");
			if(vals.length < 4)
				continue;
			int num = Integer.parseInt(vals[0]);
			if(num > 1000)
				continue;
			String key = vals[num + 1];
			int index = Integer.parseInt(vals[num + 2]);
			List<String> list = new ArrayList<String>();
			for(int i = 1; i <= num; i++){
				if(isBorth(vals[i],key))
					list.add(vals[i]);
			}
			Collections.sort(list);
			System.out.println(list.size());
			if(list.size() >= index)
				System.out.println(list.get(index  -1));
		}
	}

	public static  boolean isBorth(String source,String dest){
		if(source.equals(dest) || source.length() != dest.length())
			return false;
		for(int i = 'a'; i <= 'z'; i++){
			char ch = (char)i;
			if(getCharSize(source,ch) != getCharSize(dest,ch))
				return false;
		}
		return true;
	}

	public static int getCharSize(String source,char ch){
		int count = 0;
		for(int i = 0; i < source.length(); i++)
			if(source.charAt(i) == ch)
				count++;
		return count;
	}
}
