package lv.id.bonne.borderextender.configs;



import net.minecraft.util.Tuple;
import net.minecraftforge.common.ForgeConfigSpec;


/**
 * The configuration handling class. Holds all the config values.
 */
public class Configuration
{
    /**
     * The constructor for the config.
     */
    public Configuration()
    {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        this.borderMaxSize = builder.
            comment("The maximal world border size.").
            define("border_size", 20000);

        this.onlyCrystalOwner = builder.
            comment("Indicates if border should be increased only by crystal owner.").
            comment("Setting it to false will increase world border by everyone who exits vault.").
            define("only_owner", true);

        this.completeIncrementMin = builder.
            comment("The minimal increment of border per completed vault.").
            define("complete_increment_min", 5);

        this.completeIncrementMax = builder.
            comment("The maximal increment of border per completed vault.").
            define("complete_increment_max", 5);

        this.surviveIncrementMin = builder.
            comment("The increment of border per survived vault.").
            define("survive_increment_min", 1);

        this.surviveIncrementMax = builder.
            comment("The increment of border per survived vault.").
            define("survive_increment_max", 1);

        this.deathIncrementMin = builder.
            comment("The increment of border per death in vault.").
            define("death_increment_min", 0);

        this.deathIncrementMax = builder.
            comment("The increment of border per death in vault.").
            define("death_increment_max", 0);

        Configuration.GENERAL_SPEC = builder.build();
    }


// ---------------------------------------------------------------------
// Section: Getters
// ---------------------------------------------------------------------


    public int getBorderMaxSize()
    {
        return this.borderMaxSize.get();
    }


    public boolean getOnlyOwner()
    {
        return this.onlyCrystalOwner.get();
    }


    public Tuple<Integer, Integer> getCompleteIncrement()
    {
        return new Tuple<>(this.completeIncrementMin.get(), this.completeIncrementMax.get());
    }


    public Tuple<Integer, Integer> getSurviveIncrement()
    {
        return new Tuple<>(this.surviveIncrementMin.get(), this.surviveIncrementMax.get());
    }


    public Tuple<Integer, Integer> getDeathIncrement()
    {
        return new Tuple<>(this.deathIncrementMin.get(), this.deathIncrementMax.get());
    }


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> borderMaxSize;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> completeIncrementMin;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> completeIncrementMax;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> surviveIncrementMin;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> surviveIncrementMax;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> deathIncrementMin;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> deathIncrementMax;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Boolean> onlyCrystalOwner;

    /**
     * The general config spec.
     */
    public static ForgeConfigSpec GENERAL_SPEC;
}
