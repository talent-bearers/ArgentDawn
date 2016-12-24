package talent.bearers.slimefusion.common.core

import net.minecraft.util.IStringSerializable

/**
 * @author WireSegal
 * Created at 10:24 PM on 12/23/16.
 */
enum class EnumPolestone : IStringSerializable {
    SAPPHIRE, SMOKESTONE, RUBY, DIAMOND, EMERALD, GARNET, ZIRCON, AMETHYST, TOPAZ, HELIODOR;

    override fun getName() = name.toLowerCase()

    companion object {
        operator fun get(meta: Int)= EnumPolestone.values().getOrElse(meta) { SAPPHIRE }

        fun getNamesFor(prefix: String) = EnumPolestone.values()
                .map { prefix + "_" + it.getName() }
                .toTypedArray()
    }
}
