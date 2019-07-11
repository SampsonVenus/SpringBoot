package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/three/five/fifteen/{num}")
	public Multiple getValue(@PathVariable int num) {

		String str = checkNumber(num);
		
		return new Multiple(str);
		
	}

	public String checkNumber(int num) {

		if (num % 15 == 0) {
			return "FUZZBUD";
		} else if (num % 3 == 0) {
			return "FUZZ";

		} else if (num % 5 == 0) {
			return "BUD";
		} else
			return "No match";

	}

	public class Multiple {
		String str = "No match";
		
		
		public String getStr() {
			return str;
		}


		public void setStr(String str) {
			this.str = str;
		}


		public Multiple(String str) {
			this.str = str;
		}
	}
	
	
	//
	@GetMapping("/fib/{num}")
	public FibNumber getNumber(@PathVariable int num) {
		List<Integer> iList = new ArrayList<>();
		FibNumber fn = new FibNumber();
		
		for (int k = 0; k < num; k++) {
			int ii = fn.calFib(k);
			iList.add(ii);
		}
		
	
		return new FibNumber(iList);	

	}
	
	
	public class FibNumber{
		List<Integer>ilist = new ArrayList<>();
		
		public FibNumber() {}
		
		public FibNumber(List<Integer>ilist) {
			this.ilist = ilist;
		}
		
		
		public int calFib(int pos) {
			
			if(pos <= 1) return pos;
			else return(calFib(pos -1) + calFib(pos -2));		
			
		}
		
		public List<Integer>getFib(){
			return ilist;
		}
		
	}
	
	
	@GetMapping("/prime/even/{num}")
	public Prime getPrime(@PathVariable int num) {
		Prime p = new Prime();
		List<Integer>iList = new ArrayList<>();
		
		for(int k = 2; k <= num; k++) {
			if (p.isPrime(k)) {
				iList.add(k);
				
			}
			
		}
		
		iList.forEach(System.out::println);
		
		List<Integer>eList = new ArrayList<>();
		for(int y = 0; y < (iList.size()- 1) ; y++) {
			if(p.isEven(iList.get(y))) {
				
				eList.add(iList.get(y));   // put value in list
			}
		}
		
		
		return new Prime(eList);   // for now
	}
	
	
	private class Prime {

		boolean isTrue = true;
		List<Integer> list = new ArrayList<>();

		public Prime() {
		};

		public Prime(List<Integer> list) {
			this.list = list;
		}

		public boolean isPrime(int n) {

			if (n == 2)
				return true; // oldest prime

			if (n % 2 == 0)
				return false; // event

			// Add
			for (int k = 3; (k * k) <=  n; k = k + 2) {
				if (n % k == 0)
					isTrue = false; // even
				
			}

			return isTrue;
		}

		public boolean isEven(int m) {

			isTrue = false;
			if (m % 2 == 0)
				isTrue = true;

			return isTrue;

		}

		public boolean isTrue() {
			return isTrue;
		}

		public void setTrue(boolean isTrue) {
			this.isTrue = isTrue;
		}

		public List<Integer> getList() {
			return list;
		}

		public void setList(List<Integer> list) {
			this.list = list;
		}

	}
	
	
	@GetMapping("/largest")
	public int getValue() {
		
		Largest ln = new Largest();
		
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 3, 2, 10, 2));
		int largest = ln.majority(list);

		System.out.println(largest);
		
		return largest;
		
		
	}
	
	
	public class Largest {

		// get the number in the list that repeats the most
		public int majority(List<Integer> list) {
			Set<Integer> setInt = new TreeSet<Integer>(list);

			System.out.println("In Set : " + setInt);
			System.out.println("In List: " + list);

			Map<Integer, List<Integer>> map = new HashMap<>();
			int setKey = 0;

			List<Integer> iList = new ArrayList<>();

			Iterator<Integer> it = setInt.iterator(); // user Iterator to iterate list since TreeSet does not have an
														// index

			while (it.hasNext()) { // iterate Set

				setKey = it.next();
				List<Integer> mapInt = new ArrayList<>();

				for (int k = 0; k < list.size(); k++) { // iterate list
					int valueForMap = list.get(k);

					if (setKey == valueForMap) {
						mapInt.add(valueForMap);
					}
				}

				map.put(setKey, mapInt);

				System.out.println(map);

			}

			List<Integer> largest = new ArrayList<>();
			int size = 0;
			for (Entry<Integer, List<Integer>> entry : map.entrySet()) {

				if (size < entry.getValue().size()) {
					size = entry.getValue().size();
					largest = entry.getValue();

				}

			}

			return largest.get(size - 1); // number that repeat the most in the list

		}

	}
}
