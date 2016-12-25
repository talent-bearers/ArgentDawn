package talent.bearers.slimefusion.common.block

/**
 * @author WireSegal
 * Created at 11:00 PM on 12/23/16.
 */
object ModBlocks {
    val MARK: BlockMark
    val COLORFUL_LOG: BlockLogColorful
    val DARK_LOG: BlockLogDark
    val PLANKS: BlockPlanks

    init {
        MARK = BlockMark()
        COLORFUL_LOG = BlockLogColorful()
        DARK_LOG = BlockLogDark()
        PLANKS = BlockPlanks()
    }
}
