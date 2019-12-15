class DictionaryTest {
public static void main(String[] args){
  Dictionary test = new Dictionary();


  test.insert("ab", "1");
  test.insert("v", "1");
  System.out.println(test.isEmpty());
  System.out.println(test.size());
  test.delete("ab");
  test.insert("p","3");
}
}
