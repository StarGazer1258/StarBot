package com.stargazer1258.starcat.plugins;

import com.stargazer1258.starcat.Command;

import java.util.Random;

public class LartPlugin extends Plugin {

	private static final String pluginName = "Luser Attitude Readjustment Tool",
			shortName = "lart",
			pluginAuthor = "SpickNSpan",
			pluginVersion = "1.0",
			pluginDescription = "The Luser Attitude Readjustment Tool is an essential " +
					"item in the toolkit of every Bastard Operator From Hell (BOFH). " +
					"The LART classic is a 2x4 or other large billet of wood usable as " +
					"a club, to be applied upside the head of spammers and other " +
					"people who cause sysadmins more grief than just naturally goes " +
					"with the job. Perennial debates rage on alt.sysadmin.recovery over " +
					"what constitutes the truly effective LART; knobkerries, automatic " +
					"weapons, flamethrowers, and tactical nukes all have their partisans.";

	private static final Random rand = new Random();
	private String[] larts;

	public LartPlugin() {
		super(pluginName, shortName, pluginAuthor, pluginVersion, pluginDescription);
		commandList.add(new Command("lart", true).addUsage("lart [user]"));
	}

	@Override
	protected boolean cleanUp() {
		return false;
	}

	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (message.trim().startsWith("!lart")) {
			String news = message.trim();
			if (news.split(" ").length > 1) {
				news = news.split(" ")[1];
				if (!news.equals("!lart")) lart(news, channel);
			} else lart(sender, channel);
		}
	}

	@Override
	protected void load() {
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
				"pulls out their louisville slugger and uses #\'s head to break the homerun record",
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
	}

	private void lart(final String user, final String channel) {
		int x = (rand.nextInt() % larts.length);
		if (x < 0) x *= -1;
		sendAction(channel, larts[x].replaceAll("#", user));
	}


}
