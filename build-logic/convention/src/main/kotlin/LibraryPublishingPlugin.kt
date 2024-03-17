import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.register

/**
 * Logic which is responsible for publishing modules to Maven
 * */
class LibraryPublishingPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            tasks.register("publishModules", Exec::class) {
                val taskName = "publish"
                val modules = prepareModulesList()
                commandLine(
                    "sh",
                    "./script/deploy/build-libraries.sh",
                    *modules.map { "$it:$taskName" }.toTypedArray()
                )
            }
        }
    }

    private fun Project.prepareModulesList(): List<String> {
        return listOf(
            ":common:common-di",
            ":common:common-domain",
            ":common:common-database",
            ":common:common-network",
            ":navigation",
            ":core",
            ":uicomponents",
            ":features:home:feature-home-domain",
            ":features:home:feature-home",
            ":features:profile:feature-profile-domain",
            ":features:profile:feature-profile"
        ).sortedByDependencies(this)
    }

    private fun List<String>.sortedByDependencies(project: Project): List<String> {
        val projectPathSet = toHashSet()
        val subProjects = project.subprojects.filter { projectPathSet.contains(it.path) }
        val orderedProjectPaths = LinkedHashSet<String>()
        subProjects.forEach { project.addAllDependencies(it, orderedProjectPaths) }
        return orderedProjectPaths.toList()
    }

    private fun Project.addAllDependencies(project: Project, dependencies: LinkedHashSet<String>) {
        val validDependencies = project.getValidDependencies()

        validDependencies.forEach { validDependency ->
            subprojects.find { it.name.replace('-', '_') == validDependency }?.let {
                if (dependencies.contains(it.path)) return@forEach
                addAllDependencies(it, dependencies)
            }
        }
        project.path.takeIf { !dependencies.contains(it) }?.let {
            dependencies.add(it)
        }
    }

    private fun Project.getValidDependencies(): List<String> {
        return configurations.getByName("releaseImplementation").dependencies.filter {
            it.group?.startsWith("com.deadrudolph") == true
        }.map { it.name } + configurations.getByName("releaseApi").dependencies.filter {
            it.group?.startsWith("com.deadrudolph") == true
        }.map { it.name }
    }
}
