import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lib {
	/*
	 * 从文件中获取行数
	 */
	public static int lineCount(File file) {
		BufferedReader reader = null;
		int lineCount = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			while (reader.readLine() != null) {
				lineCount++;
			}
			reader.close();
			return lineCount;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return -1;
	}

	/*
	 * 从字符串读取行数
	 */
	public static int lineCount(String string) {
		int lineCount = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '\n') {
				lineCount++;
			}
		}
		return (lineCount + 1);
	}

	/*
	 * 从文件读取单词个数
	 */
	public static int wordCount(File file) {
		BufferedReader reader = null;
		String line = null;
		int countword = 0;
		Pattern p = Pattern.compile("[a-zA-Z]{4}+[a-zA-Z0-9]{0,}");
		Matcher m = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				m = p.matcher(line);
				while (m.find()) {
					countword++;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return countword;
	}

	/*
	 * 从字符串读取单词个数
	 */
	public static int wordCount(String string) {
		Pattern p = Pattern.compile("[a-zA-Z]{4}+[a-zA-Z0-9]{0,}");
		Matcher m = null;
		m = p.matcher(string);
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}

	/*
	 * 从文件中读取字符个数
	 */
	public static int charCount(File file) {
		BufferedReader reader = null;
		int countchar = 0;
		try {
			reader = new BufferedReader(new FileReader(file));

			while (reader.read() != -1) {
				countchar++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
		return countchar;
	}

	/*
	 * 从字符串中获取字符个数
	 */
	public static int charCount(String string) {
		return string.length();
	}

	/*
	 * 从文件中读取单词频率前十
	 */
	public static List<Entry<String, Integer>> wordCountTopTen(File file) {
		Map<String, Integer> TheNumberOfWord = new HashMap<String, Integer>();// 每个单词的个数
		BufferedReader reader = null;
		String line = null;
		Pattern p = Pattern.compile("[a-zA-Z]{4}+[a-zA-Z0-9]{0,}");
		Matcher m = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				m = p.matcher(line);
				while (m.find()) {
					String word = m.group().toLowerCase();
					if (TheNumberOfWord.containsKey(word)) {
						TheNumberOfWord.put(word, TheNumberOfWord.get(word) + 1);
					} else {
						TheNumberOfWord.put(word, 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(TheNumberOfWord.entrySet());
		Comparator<Entry<String, Integer>> com = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				if (arg0.getValue() != arg1.getValue()) {
					return (arg1.getValue().compareTo(arg0.getValue()));
				} else {
					return (arg0.getKey().compareTo(arg1.getKey()));
				}
			}
		};
		Collections.sort(list, com);
		int topTen = 10;
		List<Entry<String, Integer>> TheWordInTopTen = new ArrayList<Entry<String, Integer>>();
		TheNumberOfWord.clear();
		for (Map.Entry<String, Integer> entry : list) {
			TheWordInTopTen.add(entry);
			topTen--;
			if (topTen == 0) {
				break;
			}
		}
		return TheWordInTopTen;
	}

	/*
	 * 从字符串中获取单词前十
	 */
	public static List<Entry<String, Integer>> wordCountTopTen(String string) {
		Map<String, Integer> TheNumberOfWord = new HashMap<String, Integer>();// 每个单词的个数
		Pattern p = Pattern.compile("[a-zA-Z]{4}+[a-zA-Z0-9]{0,}");
		Matcher m = p.matcher(string);
		while (m.find()) {
			String word = m.group().toLowerCase();
			if (TheNumberOfWord.containsKey(word)) {
				TheNumberOfWord.put(word, TheNumberOfWord.get(word) + 1);
			} else {
				TheNumberOfWord.put(word, 1);
			}
		}
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(TheNumberOfWord.entrySet());
		Comparator<Entry<String, Integer>> com = new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				if (arg0.getValue() != arg1.getValue()) {
					return (arg1.getValue().compareTo(arg0.getValue()));
				} else {
					return (arg0.getKey().compareTo(arg1.getKey()));
				}
			}

		};
		Collections.sort(list, com);
		int topTen = 10;
		List<Entry<String, Integer>> TheWordInTopTen = new ArrayList<Entry<String, Integer>>();
		TheNumberOfWord.clear();
		for (Map.Entry<String, Integer> entry : list) {
			TheWordInTopTen.add(entry);
			topTen--;
			if (topTen == 0) {
				break;
			}
		}
		return TheWordInTopTen;
	}
}
