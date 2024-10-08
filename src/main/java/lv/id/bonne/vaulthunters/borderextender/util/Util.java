//
// Created by BONNe
// Copyright - 2024
//


package lv.id.bonne.vaulthunters.borderextender.util;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import iskallia.vault.core.vault.influence.VaultGod;
import iskallia.vault.core.vault.player.Completion;
import lv.id.bonne.vaulthunters.borderextender.BorderExtendMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.border.WorldBorder;


public class Util
{
    public static void increaseWorldBorder(ServerPlayer player, Completion completion)
    {
        WorldBorder worldBorder = player.getServer().overworld().getWorldBorder();
        double currentSize = worldBorder.getSize();

        Tuple<Integer, Integer> incrementRange = switch (completion)
        {
            case BAILED -> BorderExtendMod.CONFIGURATION.getSurviveIncrement();
            case FAILED -> BorderExtendMod.CONFIGURATION.getDeathIncrement();
            case COMPLETED -> BorderExtendMod.CONFIGURATION.getCompleteIncrement();
        };

        final int increment;

        if (incrementRange.getA() > incrementRange.getB())
        {
            increment = player.getLevel().random.nextInt(incrementRange.getA() - incrementRange.getB() + 1) +
                incrementRange.getB();
        }
        else if (incrementRange.getA() < incrementRange.getB())
        {
            increment = player.getLevel().random.nextInt(incrementRange.getB() - incrementRange.getA() + 1) +
                incrementRange.getA();
        }
        else
        {
            increment = incrementRange.getA();
        }

        double newSize = currentSize + increment;

        if (newSize > BorderExtendMod.CONFIGURATION.getBorderMaxSize() || newSize == currentSize)
        {
            // Nothing to do.
            return;
        }

        long overTime = worldBorder.getLerpRemainingTime() + 5 * 1000L;

        worldBorder.lerpSizeBetween(currentSize, newSize, overTime);

        String s = increment == 1 || increment == -1 ? "" : "s";

        if (newSize > currentSize)
        {
            Util.getRandom(Arrays.stream(VaultGod.values()).toList()).ifPresent(god ->
            {
                Component component = new TranslatableComponent("increment." + god.toString().toLowerCase(),
                    player.getDisplayName().copy().withStyle(ChatFormatting.BLUE),
                    new TextComponent(String.valueOf(increment)).withStyle(ChatFormatting.DARK_GREEN),
                    new TranslatableComponent("block" + s)).
                    withStyle(ChatFormatting.WHITE);

                Util.sendGodMessageToAll(player.getServer(), component, god);
            });
        }
        else
        {
            Util.getRandom(Arrays.stream(VaultGod.values()).toList()).ifPresent(god ->
            {
                Component component = new TranslatableComponent("decrement." + god.toString().toLowerCase(),
                    player.getDisplayName().copy().withStyle(ChatFormatting.BLUE),
                    new TextComponent(String.valueOf(-increment)).withStyle(ChatFormatting.DARK_RED),
                    new TranslatableComponent("block" + s)).
                    withStyle(ChatFormatting.WHITE);

                Util.sendGodMessageToAll(player.getServer(), component, god);
            });
        }
    }


    /**
     * This method returns random element from given list.
     * @param input List of input elements.
     * @return Optional of random element from list.
     */
    public static <T> Optional<T> getRandom(List<T> input)
    {
        int count = (int) (input.size() * Math.random());

        return input.stream().skip(count).findAny();
    }


    /**
     * This method sends all players in given level given message.
     * @param level Level that receives message.
     * @param text The message.
     */
    public static void sendGodMessageToAll(MinecraftServer level, Component text, VaultGod... god)
    {
        Optional<VaultGod> randomGod;

        if (god == null || god.length == 0)
        {
            randomGod = Util.getRandom(Arrays.stream(VaultGod.values()).toList());
        }
        else
        {
            randomGod = Util.getRandom(Arrays.stream(god).toList());
        }

        randomGod.ifPresent(sender ->
        {
            TextComponent senderTxt = new TextComponent("");
            senderTxt.append(Util.getGodMessage(sender));
            senderTxt.append(new TextComponent(": ").withStyle(Style.EMPTY.withColor(ChatFormatting.WHITE)));
            senderTxt.append(text);

            level.getPlayerList().getPlayers().forEach(player -> player.sendMessage(senderTxt, ChatType.SYSTEM, net.minecraft.Util.NIL_UUID));
        });
    }


    private static MutableComponent getGodMessage(VaultGod god)
    {
        return new TextComponent("[VG] ").
            append((new TextComponent(god.getName())).withStyle(god.getChatColor())).
            withStyle(style -> style.
                withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    god.getHoverChatComponent())).
                withColor(ChatFormatting.DARK_PURPLE));
    }
}
