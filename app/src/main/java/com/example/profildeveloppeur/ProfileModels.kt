package com.example.profildeveloppeur

data class ProjectUi(
    val id: Int,
    val title: String,
    val company: String,
    val period: String,
    val description: String,
    val technologies: List<String>
)

fun sampleProjects(): List<ProjectUi> {
    return listOf(
        ProjectUi(
            id = 1,
            title = "Développeur full-stack",
            company = "Alternance en entreprise",
            period = "2024 - 2026",
            description = "Développement d'applications internes en C#/Angular, du backend à l'interface utilisateur.",
            technologies = listOf("C#", "Angular")
        ),
        ProjectUi(
            id = 2,
            title = "Site portfolio personnel",
            company = "Projet personnel",
            period = "2024",
            description = "Développement d'un site vitrine avec React et TypeScript pour présenter mes projets.",
            technologies = listOf("React", "TypeScript")
        ),
        ProjectUi(
            id = 3,
            title = "Projet académique",
            company = "IUT de Reims",
            period = "2023",
            description = "Réalisation d'une application web avec Symfony et React dans le cadre de la formation.",
            technologies = listOf("Symfony", "React")
        )
    )
}
