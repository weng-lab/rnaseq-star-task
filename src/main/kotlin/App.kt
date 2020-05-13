import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import step.*
import util.*
import java.nio.file.*
import util.CmdRunner


fun main(args: Array<String>) = Cli().main(args)

class Cli : CliktCommand() {

    private val r1: Path by option("-r1", help = "path to read 1 FASTQ")
        .path(exists = true).required()
    private val r2: Path? by option("-r2", help = "path to read 2 FASTQ")
        .path()
    private val index: Path by option("-index", help = "path to index tarball")
        .path(exists = true).required()
    private val libraryId: String? by option("-libraryId", help = "library identifier which will be added to bam header").default("")

    private val outputPrefix: String by option("-outputPrefix", help = "output file name prefix; defaults to 'output'").default("output")
    private val outputDirectory: Path by option("-outputDirectory", help = "path to output Directory")
        .path().required()
    private val cores: Int by option("-cores", help = "number of cores available.").int().default(1)
    private val ramGB: Int by option("-ramGB", help = "amount of RAM available in GB").int().default(16)

    override fun run()
        = DefaultCmdRunner().align(
            AlignmentParameters(
                r1 = r1,
                r2 = r2,
                index = index,
                libraryId = libraryId,
                outputPrefix = outputPrefix,
                outputDirectory = outputDirectory,
                cores = cores,
                ram = ramGB
            )
        )

}
