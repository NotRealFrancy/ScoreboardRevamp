package org.github.notrealfrancy.scoreboardrevamp.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.github.notrealfrancy.scoreboardrevamp.ScoreboardRevamp;
import org.github.notrealfrancy.scoreboardrevamp.utils.ChatUtils;

import java.util.List;

public class RevampedScoreboard {

    @Getter private final String title;
    @Getter private final List<String> content;
    @Getter private final Player player;
    @Getter private final String[] score;

    @Getter @Setter private Scoreboard scoreboard;
    @Getter @Setter private Objective objective;

    public RevampedScoreboard(String title, List<String> content, Player player) {
        this.title = title;
        this.content = content;
        this.scoreboard = null;
        this.player = player;
        this.score = new String[content.size()];

        this.setupScoreboard();
    }

    public void setupScoreboard() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective(this.player.getName(), this.player.getName() + "-0");

        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        this.setContent();
    }

    public void setContent() {
        this.objective.setDisplayName(ChatUtils.color(this.translate(this.title)));

        int j = this.content.size();
        String content = "";
        for(String line : this.content) {
            line = ChatUtils.color(this.translate(line));

            if(line.isEmpty()) {
                content += "§r";
                line = content;
            }

            this.score[(j - 1)] = line;
            this.objective.getScore(line).setScore(j);

            j--;
        }
    }

    public void show() {
        this.player.setScoreboard(this.scoreboard);
        ScoreboardRevamp.getInstance().getScoreboardManager().add(this);
    }

    public void hide() {
        this.player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        this.objective.unregister();
        ScoreboardRevamp.getInstance().getScoreboardManager().remove(this);
    }

    public void update() {
        for(String score : this.score) {
            this.scoreboard.resetScores(score);
        }

        this.setContent();
        this.player.setScoreboard(this.scoreboard);
    }

    public String translate(String str) {
        return str
                .replaceAll("%SERVER_ONLINE%", String.valueOf(this.player.getWorld().getPlayers().size()))
                .replaceAll("%ONLINE%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replaceAll("%SERVER_NAME%", this.player.getWorld().getName())
                ;
    }

}
