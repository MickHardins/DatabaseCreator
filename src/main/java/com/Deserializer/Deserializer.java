package com.Deserializer;

/**
 * Created by Mick on 19/12/2014.
 */
public class Deserializer
{


    public static void main(String[] args) throws IOException,SQLException
    {

        InputStream inputStream = new FileInputStream("C:/Dati/Mick/MTGAPP/AllSetsArray-x-formatted.json");
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();

        MTGSet[] arr = gson.fromJson(reader,MTGSet[].class);

        //converto l'array in arraylist, forse inutilmente//
        ArrayList<MTGSet> sets = new ArrayList<MTGSet>(Arrays.asList(arr));

        // Scorri ogni set //
        for(MTGSet set_x : sets) {

            /*ottieni tutte le carte del set*/

            ArrayList<MTGCard> cards = set_x.getCards();

            //Per ogni carta del set aggiorna i campi setCode,setName, work_legalities
            for( MTGCard card_x : cards) {

                card_x.setSetName(set_x.getName());
                card_x.setSetCode(set_x.getCode());
                hashLegalitiesToObject(card_x);
                System.out.println(card_x.getName()+" Correttamente processata" + "setName: " + card_x.getSetName());
            }
        }
        System.out.println("Finita conversione legalities da hash a oggetto");
        pauperLegalitiesAdder(sets);
        System.out.println("Finita aggiunta di pauper legalities");

        UpdatedSetsContainer uptodatesets = new UpdatedSetsContainer(sets);


        ConnectionSource connection = new JdbcConnectionSource("jdbc:sqlite:C:/mydatabase.db");
        Dao<MTGSet,Long> MTGSetDAO = DaoManager.createDao(connection, MTGSet.class);
        Dao<MTGCard,Long> MTGCardDAO = DaoManager.createDao(connection,MTGCard.class);
        Dao<MTGCardLegalities, Long> MTGCardLegalitiesDAO = DaoManager.createDao(connection, MTGCardLegalities.class);

        TableUtils.createTable(connection,MTGSet.class);
        TableUtils.createTable(connection,MTGCard.class);
        TableUtils.createTable(connection,MTGCardLegalities.class);

        MTGSet lea = uptodatesets.getUpdated_sets().get(0);
        MTGSetDAO.create(lea);




    }


}




