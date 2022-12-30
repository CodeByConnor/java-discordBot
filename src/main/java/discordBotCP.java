//import statements
import commands.botCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class discordBotCP {

    public static void main(String[] args) throws LoginException {

        JDA bot = JDABuilder.createDefault("token") //actual token hidden
                .setActivity(Activity.listening("listening to chat"))
                .addEventListeners(new botCommands())
                .build();

        JDABuilder jda = JDABuilder.createDefault("token"); //actual token hidden
        jda.setActivity(Activity.watching("watching the charts"));
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.setMemberCachePolicy(MemberCachePolicy.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jda.build();


    }
}
