package talent.bearers.slimefusion.common.core

import talent.bearers.slimefusion.common.block.base.EnumStringSerializable

/**
 * @author WireSegal
 * Created at 8:41 AM on 12/27/16.
 */
enum class EnumSlimeType : EnumStringSerializable {
    WHITE, RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    companion object {
        operator fun get(meta: Int) = EnumSlimeType.values().getOrElse(meta) { WHITE }

        fun getNamesFor(prefix: String) = EnumSlimeType.values()
                .map { prefix + "_" + it.getName() }
                .toTypedArray()
    }
}
