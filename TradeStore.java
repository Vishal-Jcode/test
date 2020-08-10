package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
//import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import test.Trade;


class TradeStore
{
	private Trade trade;
	public Date StringToDate(String dob) throws ParseException {
		//Instantiating the SimpleDateFormat class
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		//Parsing the given String to Date object
		Date date = formatter.parse(dob);
		//System.out.println("Date object value: "+date);
		return date;
	}

	public void printTrades(TreeMap<String, ArrayList<Trade>> map){
		System.out.println("Displaying trade data \n");
		System.out.println("Trade Id \t Version \t CounterPartyId \t Book-Id \t Maturity Date \t Created Date \t Expired");
		for(String s : map.keySet() ){
			map.get(s).stream().sorted((s1,s2) -> (s2.getVersion()-s1.getVersion())).forEach(name -> {
				System.out.println(name);});
		}
	}

	public void refresh(TreeMap<String, ArrayList<Trade>> map){
		System.out.println("Refreshing the trade data..");
		Date date = new Date();
	//	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//	LocalDate localDate = LocalDate.now();

		for(String s : map.keySet() ){
			int size = map.get(s).size();
			for(int i = 0; i < size ; i++){
				try {
					if(date.compareTo(StringToDate(map.get(s).get(i).getMaturityDate())) > 0){
						map.get(s).get(i).setExpired("Y");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	}

	public static void main (String args[]) throws ParseException, MyException
	{
		ArrayList<Trade> list;
		TreeMap<String, ArrayList<Trade>> map = new TreeMap<String,ArrayList<Trade>>(new MyComp());
		//Path filePath = Paths.get("F:/Java_work/Day1/bin/test", "TradeData.txt");
		File file = new File("F:/Java_work/Day1/bin/test/TradeData.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;
			while((line = br.readLine()) != null)
			{
				String []arr = line.split("[|]");
				if(!map.containsKey(arr[0])){
					//list = (ArrayList<Trade>) IntStream.range(0,0).mapToObj(i -> new Trade(arr[0],Integer.parseInt(arr[1]),arr[2],arr[3],arr[4],arr[5],arr[6]));
					Trade trade = new Trade(arr[0],Integer.parseInt(arr[1]),arr[2],arr[3],arr[4],arr[5],arr[6]);
					list = new ArrayList<Trade>();
					list.add(trade);
					map.put(arr[0], list);
				}else{
					int size = map.get(arr[0]).size();
					Integer version = map.get(arr[0]).get(size - 1).getVersion();

					if(version.equals(Integer.parseInt(arr[1]))){
		//				System.out.println(arr[0] + "  " + Integer.parseInt(arr[1]) + "  " + version + "size : " + size);
						map.get(arr[0]).set(size - 1, new Trade(arr[0],Integer.parseInt(arr[1]),arr[2],arr[3],arr[4],arr[5],arr[6]));
					}else if(Integer.parseInt(arr[1]) > version){
						map.get(arr[0]).add(new Trade(arr[0],Integer.parseInt(arr[1]),arr[2],arr[3],arr[4],arr[5],arr[6]));
					}else{
						System.out.println("Error : invalid/lower trade version found for trade : " + arr[0] + "  version : " + version);
						throw new MyException("Error : invalid/lower trade version found for trade");
					//	assert 1==1 : "Error : invalid/lower trade version found for trade";
					}

				}

			}
			br.close();
			fr.close();
			
			TradeStore st = new TradeStore();
			st.printTrades(map);
			st.refresh(map);
			System.out.println("============================================================");
			st.printTrades(map);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e1){
			e1.printStackTrace();
		}
		catch(Exception e2){
			e2.printStackTrace();
		}

		
	}
	
	
	
}

class MyException extends Exception {
	   String desc;

	   public MyException(String x) {
	      desc = x;
	   }

	   public String toString() {
	      return "CustomException[" + desc + "]";
	   }
	}

class MyComp implements Comparator<String> {

	  @Override
	  public int compare(String str1, String str2) {
	     String notDigit = "[^\\d]";
	     int int1 = Integer.parseInt(str1.replaceAll(notDigit, ""));
	     int int2 = Integer.parseInt(str2.replaceAll(notDigit, ""));
	     return Integer.compare(int1, int2);
	  }
	}