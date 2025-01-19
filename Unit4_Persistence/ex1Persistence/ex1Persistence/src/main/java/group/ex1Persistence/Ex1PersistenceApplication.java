package group.ex1Persistence;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group.ex1Persistence.entities.EntitiesEnum;
import group.ex1Persistence.entities.EntitiesEnum.W_Gender;
import group.ex1Persistence.entities.EntitiesEnum.W_Number;
import group.ex1Persistence.entities.EntitiesEnum.W_Person;
import group.ex1Persistence.entities.Sentence;
import group.ex1Persistence.entities.SentencedWord;
import group.ex1Persistence.entities.SentencedWordId;
import group.ex1Persistence.entities.Sustantive;
import group.ex1Persistence.entities.Verb;
import group.ex1Persistence.entities.Word;
import group.ex1Persistence.repositories.SentenceRepository;
import group.ex1Persistence.repositories.SentencedWordRepository;
import group.ex1Persistence.repositories.SustantiveRepository;
import group.ex1Persistence.repositories.VerbRepository;
import group.ex1Persistence.repositories.WordRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication(scanBasePackages="group.ex1Persistence")
public class Ex1PersistenceApplication implements CommandLineRunner  {

	@Autowired
	private  WordRepository wordRepo;
	@Autowired
	private  SustantiveRepository sustRepo;
	@Autowired
	private  VerbRepository verbRepo;

	@Autowired
	private  SentencedWordRepository sentWRepo;
	
	@Autowired
	private  SentenceRepository sentRepo;

	public static void main(String[] args) {
		SpringApplication.run(Ex1PersistenceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		funcionPruebas1();
	}

	@Transactional
	public void funcionPruebas1() {

		System.out.println("");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("*** PLAYING WITH BASIC  *************************************");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("");

		System.out.println("");
		System.out.println("INSERT WORD ROW ");
		Word word =new Word("Hola", null, "Hallo");
		wordRepo.save(word);
		System.out.println(wordRepo.findById("Hallo").toString());

		System.out.println("");
		System.out.println("INSERT SUSTANTIVE ROW ");
		sustRepo.save(new Sustantive(
				new Word("Habitacion", null,"Zimmer"),
				W_Number.SINGULAR, W_Gender.MASCULINE, W_Gender.MASCULINE));
		System.out.println(sustRepo.findById("Zimmer").toString());

		System.out.println("");
		System.out.println("IF WE DELETE THE PARENT ROW THE SON ROW IS DELETED ");
		wordRepo.deleteById("Zimmer");
		System.out.println(sustRepo.findById("Zimmer").toString());

		System.out.println("");
		System.out.println("IF WE INSERT VERB ROW WITH verbRepo AND WE SEARCH  WITH " +
				"wordRepo, IT RETURNS A VERB");
		verbRepo.save(new Verb(
				new Word( "Hablar", null, "Sprechen")
				, W_Number.PLURAL, W_Person.THIRD, "Infinitive, present"));
		System.out.println(wordRepo.findById("Sprechen").toString());
		
		System.out.println("");
		System.out.println("IF WE INSERT VERB ROW WITH wordRepo AND "
				+ "POLIMORFISM AND WE SEARCH  WITH " 
				+ "wordRepo, IT RETURNS A VERB");
		wordRepo.save((Word) new Verb(
				new Word( "Ver", null, "Sehen"),
				W_Number.PLURAL, W_Person.THIRD, "Infinitive, present"));
		System.out.println( wordRepo.findById("Sehen").get().toString());

		System.out.println("");
		System.out.println("INSERT SENTENCE ROW");
		Sentence sent = new Sentence(1, null);
		sentRepo.save(sent);

		System.out.println("");
		System.out.println("IF WE REPIT THE SAME SENTENCE ROW, IT MAKES "
				+ "A SELECT AND IF THE ROW ALREADY EXIST, IT JUST SKIP THE "
				+ "INSERT ORDER.");
		sentRepo.save(sent);
		
		System.out.println("");
		System.out.println("INSERT SENTENCEDWORD ROW");
		Word word2 =new Word( "Estornudo", null, "Niesen");
		sentWRepo.save(new SentencedWord(sent, 1, word2));
		System.out.println(sentWRepo.findById(new SentencedWordId(sent, 1)));

		System.out.println("");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("*** EXECUTING MY OWN SELECT *************************************");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("");

		System.out.println("");
		System.out.println("EXECUTING MY OWN SELECT "
				+"wordRepo.findfindBySpanishWord(\"Estornudo\")");
		
		Word word4 =new Word( "Estornudo", null, "niesen");
		wordRepo.save(word4);
		Word word5 =new Word( "Estornudo", null, "niese");
		wordRepo.save(word4);
		wordRepo.save(word5);
		
		ArrayList<Word> wordList = 
			(ArrayList<Word>) wordRepo.findBySpanishWord("Estornudo");

		if (wordList!=null) {
			System.out.println(wordList.size());
			for (Word word3 : wordList) {
				if (word3!=null){
					System.out.println(( word3).toString());
				}
			}
		}
		
		System.out.println("");
		System.out.println("EXECUTING MY OWN SELECT "
				+"verbRepo.findByNumber(EntitiesEnum.W_Person.THIRD.name())");
		ArrayList<Verb> verbList = 
			(ArrayList<Verb>) verbRepo.findByNumber(EntitiesEnum.W_Person.THIRD.name());
		
		if (verbList!=null) {
			System.out.println(verbList.size());
			for (Verb word3 : verbList) {
				if (word3!=null){
					System.out.println(( word3).toString());
				}
			}
		}

		System.out.println("");
		System.out.println("EXECUTING MY OWN SELECT sustRepo.findBySpanis"
		+ "hGender(EntitiesEnum.W_Gender.MASCULINE.name()");
		ArrayList<Sustantive> sustList = 
			(ArrayList<Sustantive>) sustRepo.findBySpanishGender(
				EntitiesEnum.W_Gender.MASCULINE.name()
				);
		
		if (sustList!=null) {
			System.out.println(sustList.size());
			for (Sustantive word3 : sustList) {
				if (word3!=null){
					System.out.println(( word3).toString());
				}
			}
		}

		System.out.println("");
		System.out.println("EXECUTING MY OWN SELECT "
			+ "sentRepo.findUserNoteById(int id)");
		
		String strUserNote = sentRepo.findUserNoteById(1);
		System.out.println("Res UserNote id 1: " + nonNullNonEmpty(strUserNote));

		sentRepo.save(new Sentence(2, "user note example"));
		strUserNote = sentRepo.findUserNoteById(2);
		System.out.println("Res UserNote id 2: " + nonNullNonEmpty(strUserNote));

		strUserNote = sentRepo.findUserNoteById(3);
		System.out.println("Res UserNote id 3: " + nonNullNonEmpty(strUserNote));

		System.out.println("");
		System.out.println("EXECUTING MY OWN SELECT "
			+ "sentWRepo.findFullSentenceArrayListById(1)");

		word2 =new Word( "dos", null, "zwei");
		sentWRepo.save(new SentencedWord(sent, 2, word2));

		ArrayList<Object[]> lista= sentWRepo.findFullSentenceArrayListById(1);
		toStringWordList(lista);

		System.out.println("");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("*** EXECUTING MY OWN SELECT *************************************");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("");
	}

	private void toStringWordList(ArrayList<Object[]> wordList){
		if (wordList!=null) {
			for (Object[] word3 : wordList) {
				if (word3!=null){
					for (int i = 0; i<word3.length; i++) {
						if (word3[i]!=null) {
							System.out.println(nonNullNonEmpty(word3[i].toString()).toString());
						}else{
							System.out.println("null");
						}
					}
				}
			}
		}
	}
	private String nonNullNonEmpty(String str){
		if (str==null) {
			str = "null";
		} else if (str.length()==0) {
			str = "empty";
		}
		return str;
	}

}
