
package commands;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent; //update maven dependency, missing class
import net.dv8tion.jda.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import javax.management.relation.Role;
import java.awt.*;
import java.lang.reflect.Member;

public class botCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        if(event.getName().equals("something")){ //user defined
            event.reply("you said something").queue();
        }
    }
    public void onGuildMessageReceived(GuildMessageRecieved event){
        String [] args = event.getMessage().getContentRow().split(" ");

        if(args[0].equalsIgnoreCase("!")) {
            EmbedBuilder ebd = new EmbedBuilder();
            ebd.setTitle("This is the title of my embed", "");
            ebd.setDescription("This is the embed description");
            ebd.addField("Embed Field 1", "This is the content of Field 1", false);
            ebd.addField("Embed Field 2", "This is the content of Field 2", false);
            ebd.addField("Embed Field 3", "This is the content of Field 3", false);
            ebd.setColor(Color.WHITE);
            ebd.setFooter("Bot created by <@1421839869812408341", event.getGuild().getOwner().getUser().getAvatarUrl());
            event.getChannel().sendMessage(ebd.builder().queue());
            ebd.clear();
        }
        if (args[0].equalsIgnoreCase("!" + "given role")){
            if(event.getMessage().getMentionedRoles().toArray().length == 1){
                Member m = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
                Role giveRole = event.getMessage().getMentionedRoles().get(0);
                event.getGuild().addRoleToMember(m, giveRole).queue();
                event.getMessage().reply("Gave the role" + giveRole.getAsMention() + "to" + m.getAsMention());

            }
            else{
                event.getMessage().reply("You can only mention 1 role.");
            }
        }
        else{
            event.getMessage().reply("You can only mention 1 role.");
        }
    }
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }

}
