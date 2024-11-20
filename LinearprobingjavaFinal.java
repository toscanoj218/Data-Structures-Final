import java.util.*; 
import java.io.File; 
import java.io.FileNotFoundException; 
class LinearProbingJavaFinal 
{ 
    // Display hash table 
    static void printArray(int arr[], int collisions[]) 
    {
         System.out.println("Index" + "\t" + "Data" + "\t" + "Probes"); 
         for (int i = 0; i < arr.length; i++) 
         {
             System.out.println(i + "\t" + arr[i] + "\t" + collisions[i]); 
        } 
    } 

    // Calculate ASCII value 
    static int calculateAscii(String str) 
    {
         int total = 0; 
         for (char c : str.toCharArray()) 
         {
             total += c; 
        } 
        return total; 
    } 

    // Hashing using Linear Probing 
    static void hashing(int table[], int collisions[], int tsize, String arr[], int N) 
    {
         int totalCollisions = 0; 
         int maxCollisions = 0; 
         int emptyPositions = tsize; 
         for (int i = 0; i < tsize; i++) 
         {
             table[i] = -1; 
             collisions[i] = 0; 
        }
         for (int i = 0; i < N; i++) 
         {
             int hv = calculateAscii(arr[i]) % tsize; 
             if (table[hv] == -1) 
             {
                 table[hv] = calculateAscii(arr[i]); 
             } 
        else 
        {
             for (int j = 0; j < tsize; j++) 
             {
                 int t = (hv + j) % tsize; 
                 if (table[t] == -1) 
                 {
                     table[t] = calculateAscii(arr[i]); 
                     collisions[t]++; 
                     totalCollisions++; 
                     emptyPositions--; 
                     break; 
                }
                 
                else 
                {
                     collisions[t]++; 
                     totalCollisions++; 
                }
            }
        }
    } 
    for (int i = 0; i < tsize; i++) 
    {
         if (collisions[i] > maxCollisions) 
         {
             maxCollisions = collisions[i]; 
         } 
    } 
    double avgCollisions = (double) totalCollisions / N; 
    printArray(table, collisions); 
    System.out.println("Total Collisions: " + totalCollisions); //display total collisions
    System.out.println("Maximum Collisions: " + maxCollisions); //display maximum collisions
    System.out.println("Average Collisions: " + avgCollisions); //display averge collsions
    System.out.println("Empty Positions: " + emptyPositions); //display empty positions
} 
    // Search word in hash table used to search for the 50 words
    static void searchWord(int[] hashTable, int[] collisions, String word) 
    {
         long startTime = System.nanoTime(); //creating our start time
         int asciiValue = calculateAscii(word); //converting our word to ascii value
         int index = asciiValue % hashTable.length; 
         boolean found = false; //setting found to false
         for (int i = 0; i < hashTable.length; i++) 
         {
             int t = (index + i) % hashTable.length; 
             if (hashTable[t] == asciiValue) 
             {
                 found = true; break; //ends when word is found by setting found to true
             }
         } 
         long endTime = System.nanoTime();//creeating our end time 
         long timeTaken = (endTime - startTime) / 1000000; //finding our time taken by subtracting end and start time
         if (found) 
         {
             System.out.println(word + " found in " + timeTaken + " milliseconds"); //display our time 
            }
             else 
             {
                 System.out.println(word + " not found"); //display when word was not found
             } 
        } 
        public static void main(String args[]) 
        {
             File inputFile = new File("words_a.txt"); //allwoing program to read file
             int N = 466551;//table size for file 


             // Hash table prime .7 LF 
             //int S = 40;//test
            int S = 765211;//table size
             int[] hashTablePrime7 = new int[S]; 
             int[] collisionPrime7 = new int[S]; 

             // Hash table prime .5 LF 
             //int F = 43;//turn on for test
            int F = 998651;//table size
             int[] hashTablePrime5 = new int[F]; 
             int[] collisionPrime5 = new int[F];

             // Hash table non-prime .7 LF 
             //int O = 32;//turn on for test
            int O = 689900;//table size
             int[] hashTableNonPrime7 = new int[O]; 
             int[] collisionNonPrime7 = new int[O];

             // Hash table non-prime .5 HF 
             //int H = 21;// turn on for test
            int H = 998654;//table size
             int[] hashTableNonPrime5 = new int[H]; 
             int[] collisionNonPrime5 = new int[H]; 

            //prime.7
             for (int i = 0; i < S; i++) 
             {
                 hashTablePrime7[i] = -1; 
                 collisionPrime7[i] = 0; 
             } 

             //prime.5
             for (int i = 0; i < F; i++) 
             {
                 hashTablePrime5[i] = -1; 
                 collisionPrime5[i] = 0; 
             } 

             //nonprime.7
             for (int i = 0; i < O; i++) 
             {
                 hashTableNonPrime7[i] = -1; 
                 collisionNonPrime7[i] = 0; 
             }

             //nonprime.5
            for(int i = 0; i < H; i++) 
             { 
                hashTableNonPrime5[i] = -1; 
                collisionNonPrime5[i] = 0; 
             } 

             ArrayList<String> wordsList = new ArrayList<>(); 
             try 
             {
                 Scanner in = new Scanner(inputFile); 
                 while (in.hasNextLine()) 
                 {
                     String word = in.nextLine(); 
                     wordsList.add(word); 
                 } 
                 in.close(); 
            } 
            catch (FileNotFoundException e) //catch file to make sure file is able to be read
            {
                 System.out.println("File not found"); 
            }
                 String[] words = wordsList.toArray(new String[0]); 
                
                 //display and hash prime .7
                 System.out.println("Hash table prime number with load factor .7 after hashing:"); 
                 hashing(hashTablePrime7, collisionPrime7, S, words, words.length); 
                 
                 //display and hash prime .5
                 System.out.println("Hash table prime number with load factor .5 after hashing:"); 
                 hashing(hashTablePrime5, collisionPrime5, F, words, words.length); 
                 
                 //display and hash nonprime .7
                 System.out.println("Hash table non-prime number with load factor .7 after hashing:"); 
                 hashing(hashTableNonPrime7, collisionNonPrime7, O, words, words.length); 
                 
                //display and hash nonprime .5
                 System.out.println("Hash table non-prime number with load factor .5 after hashing:"); 
                 hashing(hashTableNonPrime5, collisionNonPrime5, H, words, words.length); 

                 //50 words
                 String searchWord1 = "hystazarin"; searchWord(hashTablePrime7, collisionPrime7, searchWord1);
                 String searchWord2 = "mules"; searchWord(hashTablePrime7, collisionPrime7, searchWord2);
                 String searchWord3 = "rebaled"; searchWord(hashTablePrime7, collisionPrime7, searchWord3);
                 String searchWord4 = "hereditaments"; searchWord(hashTablePrime7, collisionPrime7, searchWord4);
                 String searchWord5 = "apodioxis"; searchWord(hashTablePrime7, collisionPrime7, searchWord5);
                 String searchWord6 = "prefeudal"; searchWord(hashTablePrime7, collisionPrime7, searchWord6);
                 String searchWord7 = "galvanofaradization"; searchWord(hashTablePrime7, collisionPrime7, searchWord7);
                 String searchWord8 = "currachs"; searchWord(hashTablePrime7, collisionPrime7, searchWord8);
                 String searchWord9 = "gastrea"; searchWord(hashTablePrime7, collisionPrime7, searchWord9);
                 String searchWord10 = "Resuscitated"; searchWord(hashTablePrime7, collisionPrime7, searchWord10);
                 //40 words
                 String searchWord11 = "near-ushering"; searchWord(hashTablePrime7, collisionPrime7, searchWord11);
                 String searchWord12 = "asteer"; searchWord(hashTablePrime7, collisionPrime7, searchWord12);
                 String searchWord13 = "bacteric"; searchWord(hashTablePrime7, collisionPrime7, searchWord13);
                 String searchWord14 = "abwatts"; searchWord(hashTablePrime7, collisionPrime7, searchWord14);
                 String searchWord15 = "rocktree"; searchWord(hashTablePrime7, collisionPrime7, searchWord15);
                 String searchWord16 = "faveolate"; searchWord(hashTablePrime7, collisionPrime7, searchWord16);
                 String searchWord17 = "ingratiatory"; searchWord(hashTablePrime7, collisionPrime7, searchWord17);
                 String searchWord18 = "unificator"; searchWord(hashTablePrime7, collisionPrime7, searchWord18);
                 String searchWord19 = "usurpment"; searchWord(hashTablePrime7, collisionPrime7, searchWord19);
                 String searchWord20 = "Steeks"; searchWord(hashTablePrime7, collisionPrime7, searchWord20);
                 //30words
                 String searchWord21 = "hopple"; searchWord(hashTablePrime7, collisionPrime7, searchWord21);
                 String searchWord22 = "well-manured"; searchWord(hashTablePrime7, collisionPrime7, searchWord22);
                 String searchWord23 = "ovaliform"; searchWord(hashTablePrime7, collisionPrime7, searchWord23);
                 String searchWord24 = "brontothere"; searchWord(hashTablePrime7, collisionPrime7, searchWord24);
                 String searchWord25 = "Tricholoma"; searchWord(hashTablePrime7, collisionPrime7, searchWord25);
                 String searchWord26 = "incircumspectly"; searchWord(hashTablePrime7, collisionPrime7, searchWord26);
                 String searchWord27 = "swoopers"; searchWord(hashTablePrime7, collisionPrime7, searchWord27);
                 String searchWord28 = "fumed"; searchWord(hashTablePrime7, collisionPrime7, searchWord28);
                 String searchWord29 = "tidecoach"; searchWord(hashTablePrime7, collisionPrime7, searchWord29);
                 String searchWord30 = "Sloane"; searchWord(hashTablePrime7, collisionPrime7, searchWord30);
                 //20words
                 String searchWord31 = "custumal"; searchWord(hashTablePrime7, collisionPrime7, searchWord31);
                 String searchWord32 = "fibrochondrosteal"; searchWord(hashTablePrime7, collisionPrime7, searchWord32);
                 String searchWord33 = "fullwords"; searchWord(hashTablePrime7, collisionPrime7, searchWord33);
                 String searchWord34 = "evadible"; searchWord(hashTablePrime7, collisionPrime7, searchWord34);
                 String searchWord35 = "tuffets"; searchWord(hashTablePrime7, collisionPrime7, searchWord35);
                 String searchWord36 = "shirtiness"; searchWord(hashTablePrime7, collisionPrime7, searchWord36);
                 String searchWord37 = "hydroxyapatite"; searchWord(hashTablePrime7, collisionPrime7, searchWord37);
                 String searchWord38 = "Fabe"; searchWord(hashTablePrime7, collisionPrime7, searchWord38);
                 String searchWord39 = "theropodous"; searchWord(hashTablePrime7, collisionPrime7, searchWord39);
                 String searchWord40 = "IX"; searchWord(hashTablePrime7, collisionPrime7, searchWord40);
                 //10words
                 String searchWord41 = "dulias"; searchWord(hashTablePrime7, collisionPrime7, searchWord41);
                 String searchWord42 = "rubeoloid"; searchWord(hashTablePrime7, collisionPrime7, searchWord42);
                 String searchWord43 = "home-sickness"; searchWord(hashTablePrime7, collisionPrime7, searchWord43);
                 String searchWord44 = "Nedi"; searchWord(hashTablePrime7, collisionPrime7, searchWord44);
                 String searchWord45 = "myomantic"; searchWord(hashTablePrime7, collisionPrime7, searchWord45);
                 String searchWord46 = "actinologous"; searchWord(hashTablePrime7, collisionPrime7, searchWord46);
                 String searchWord47 = "gelotherapy"; searchWord(hashTablePrime7, collisionPrime7, searchWord47);
                 String searchWord48 = "untechnical"; searchWord(hashTablePrime7, collisionPrime7, searchWord48);
                 String searchWord49 = "Blagoveshchensk"; searchWord(hashTablePrime7, collisionPrime7, searchWord49);
                 String searchWord50 = "superconsecrated"; searchWord(hashTablePrime7, collisionPrime7, searchWord50);
                 
                } 
            }