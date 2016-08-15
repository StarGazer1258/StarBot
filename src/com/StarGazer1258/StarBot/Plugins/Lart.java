package starcat.plugin.plugins;

import java.util.Random;

public class Lart extends Plugin {

    private static final String pluginName = "Luser_Attitude_Readjustment_Tool",
            shortName = "lart",
            pluginAuthor = "SpickNSpan",
            pluginVersion = "if.you.think.im.maintaining.this.you're.wrong.0.1",
            pluginDescription = "The Luser Attitude Readjustment Tool is an essential " +
                    "item in the toolkit of every Bastard Operator From Hell (BOFH). " +
                    "The LART classic is a 2x4 or other large billet of wood usable as " +
                    "a club, to be applied upside the head of spammers and other " +
                    "people who cause sysadmins more grief than just naturally goes " +
                    "with the job. Perennial debates rage on alt.sysadmin.recovery over " +
                    "what constitutes the truly effective LART; knobkerries, automatic " +
                    "weapons, flamethrowers, and tactical nukes all have their partisans.";

    private static final int id = 0x306e;
    private static final Random rand = new Random();
    private String[] larts;

    public Lart()
    {
        super(pluginName, shortName, pluginAuthor, pluginVersion, pluginDescription, id);
    }

    @Override
    protected boolean cleanUp()
    {
        return false;
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        if(message.trim().startsWith("!lart"))
        {
            String news = message;
            news.trim();
            if(find(news, ' ') < news.length())
            {
                news = news.substring(find(news, ' ') + 1);
                news = news.substring(0, find(news, ' '));
                if(!news.equals("!lart")) lart(news);
            }
            else lart(sender);
        }
    }

    @Override
    protected void load()
    {
        //larts.  "#" will be replaced with the username.
        larts = new String[]{
                "pours gasoline on # and lights # on fire",
                "executes rm -rf's `find / -user #`",
                "knocks # over the head with a 2x4",
                "beats # to death with a pastry",
                "gets out the cluebat and beats # senseless",
                "frags # with his BFG9000",
                "takes # to the zoo to get # replaced with a better looking monkey",
                "forces # to use Internet Explorer 5.0",
                "puts # in a gif and deletes it",
                "pulls out his louisville slugger and uses #\'s head to break the homerun record",
                "decapitates # conan the destroyer style",
                "does a little \'renice 20 -u #\'",
                "pushes a wall down onto # whilst whistling innocently",
                "whacks # with a cluebat",
                "drops a stack of 350,000 floppy disks on #",
                "cats /dev/urandom into #\'s ear",
                "adds #'s code to the Windows 3.11 code base because it's so good",
                "chops # in half with a free Earthlink CD",
                "sends # a Dell",
                "kicks # in the jimmy",
                "drops a truckload of VAXen on #"
        };
        //because java doesn't have pointers, who knows how this will be allocated....
    }

    //because they didn't add a FIND function in string...
    private int find(final String s, final char c)
    {
        int x;
        boolean found = false;
        for(x = 0; x < s.length(); ++x) if(found = (s.charAt(x) == c)) break;
        if(!found) x = s.length();
        return x;
    }

    private void lart(final String user)
    {
        int x = (rand.nextInt() % larts.length);
        if(x < 0) x *= -1;
        sendMessage(user, ("/me " + larts[x].replaceAll("#", user)));
    }


}
