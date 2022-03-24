package lab13;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordFisher{
	
	public HashMap<String, Integer> vocabulary;
	private List<String> stopwords;
	private String inputTextFile;
	private String stopwordsFile;

	public WordFisher (String inputTextFile, String stopwordsFile){
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;
		
		vocabulary = new HashMap<String, Integer>();
		stopwords = new ArrayList<String>();
		getStopwords();
		buildVocabulary();
	}

	private void getStopwords(){
		try {
	     File fl = new File(stopwordsFile);
	     Scanner sc = new Scanner(fl);
	     while (sc.hasNextLine())
	    {
	       String line = sc.nextLine();
	       stopwords.add(line);
	    }
	     sc.close();
	   } catch (FileNotFoundException e) {
	     System.out.println("An error occurred.");
	     e.printStackTrace();
	   }
	}

	private void buildVocabulary(){
		String s = null;
		try {
			s = new String(Files.readAllBytes(Paths.get(inputTextFile))).replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] allWords = s.split("\\s+");

		for (String word: allWords)
		{
			if (vocabulary.containsKey(word))
			{
				int value = vocabulary.get(word);
				vocabulary.replace(word, value + 1);
			}
			else
			{
				vocabulary.put(word, 1);
			}
		}
	}

	public int getWordCount(){
		int wc = 0;

		for (Integer wFreq: vocabulary.values()){
			wc += wFreq.intValue();
		}

		return wc;
	}

	public int getNumUniqueWords(){
		return vocabulary.size();
	}

	public int getFrequency(String word){
		if (vocabulary.containsKey(word)){
			return vocabulary.get(word);
		}

		return -1;
	}

	public void pruneVocabulary(){
		for (String sw: stopwords){
			if (vocabulary.containsKey(sw))
				vocabulary.remove(sw);
		}
	}

	public ArrayList<String> getTopWords(int n){
		PriorityQueue<WordNode> pq = new PriorityQueue<WordNode>(new WordNodeComparator());
		ArrayList<String> topWords = new ArrayList<String>();

		for (String words: vocabulary.keySet()){
			pq.add(new WordNode(words, vocabulary.get(words).intValue()));
		}

		int topWordsCount = 0;

		while (topWordsCount < n && !pq.isEmpty()){
			topWords.add(pq.poll().word);
			topWordsCount++;
		}

		return topWords;
	}

	public ArrayList<String> commonPopularWords(int n, WordFisher other){
		ArrayList<String> topWordUno = getTopWords(n);
		ArrayList<String> topWordDos = other.getTopWords(n);

		topWordUno.retainAll(topWordDos);

		return topWordUno;
	}

	public class WordNodeComparator implements Comparator<WordNode>{
		public int compare(WordNode wn1, WordNode wn2){
			if (wn1.frequency < wn2.frequency){
				return 1;
			}else if (wn1.frequency == wn2.frequency){
				return 0;
			}else
				return -1;
		}
	}

	public class WordNode{
		public String word;
		public int frequency;

		public WordNode(String word, int frequency){
			this.word = word;
			this.frequency = frequency;
		}
	}

	

	public static void main(String arg[]){
		String inputTextFile="texts/moby-dick.txt";
		String stopwordsFile="stopwords.txt";
		String inputTextFile2="texts/carroll-alice.txt";
		WordFisher wf=new WordFisher(inputTextFile, stopwordsFile);
		WordFisher df =new WordFisher(inputTextFile, stopwordsFile);
		System.out.println(wf.getWordCount());
		System.out.println(wf.getNumUniqueWords());
		System.out.println(wf.getFrequency("handerchief"));
		wf.pruneVocabulary();
		System.out.println(wf.getWordCount());
		System.out.println(wf.getTopWords(10));
		df.pruneVocabulary();
		System.out.println(wf.commonPopularWords(20,df));
	
	 }
	
}