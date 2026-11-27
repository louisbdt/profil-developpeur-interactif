package com.example.profildeveloppeur

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

@Composable
fun ProfileScreen(
    name: String,
    role: String,
    location: String,
    description: String,
    skills: List<String>,
    projects: List<ProjectUi>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    email: String,
    contact: String,
    availability: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ProfileHeaderCard(
                    name = name,
                    role = role,
                    location = location,
                    description = description
                )
            }
            item {
                SkillsRow(
                    skills = skills,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "Projets",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    label = {
                        Text(text = "Rechercher un projet")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(
                items = projects,
                key = { project -> project.id }
            ) { project ->
                ProjectCard(project = project)
            }
            item {
                ContactSection(
                    email = email,
                    githubLink = contact,
                    availability = availability,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                ContactButton(
                    label = "Voir mon GitHub",
                    onClick = onContactClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ProfileContainer(
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        name = "Louis Baudat",
        role = "Étudiant en développement web à l'ESGI",
        location = "Reims, France",
        description = "Étudiant en développement web à l'ESGI. Je m'intéresse particulièrement au développement full-stack, de Symfony/PHP/C# côté backend à React/TypeScript/Angular côté frontend.",
        skills = listOf("Symfony", "React", "TypeScript", "C#", "Angular"),
        projects = uiState.projects,
        searchQuery = uiState.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        email = "contact@louisbaudat.dev",
        contact = "github.com/louisbdt",
        availability = "Disponible pour une alternance",
        onContactClick = onContactClick,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenJuniorPreview() {
    ProfilDeveloppeurTheme {
        ProfileScreen(
            name = "Louis Baudat",
            role = "Étudiant en développement web à l'ESGI",
            location = "Reims, France",
            description = "Je découvre actuellement Android et Jetpack Compose.",
            skills = listOf("Symfony", "React", "TypeScript"),
            projects = sampleProjects(),
            searchQuery = "",
            onSearchQueryChange = {},
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenFullStackPreview() {
    ProfilDeveloppeurTheme {
        ProfileScreen(
            name = "Louis Baudat",
            role = "Développeur full-stack",
            location = "Reims, France",
            description = "En alternance depuis 1 an, où je réalise des applications en C#/Angular",
            skills = listOf("C#", "Angular"),
            projects = sampleProjects(),
            searchQuery = "",
            onSearchQueryChange = {},
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    showBackground = true,
    name = "Profil - Light Theme"
)
@Composable
fun ProfileScreenLightPreview() {
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfileScreen(
            name = "Louis Baudat",
            role = "Développeur full-stack",
            location = "Reims, France",
            description = "En alternance depuis 1 an, où je réalise des applications en C#/Angular",
            skills = listOf("C#", "Angular"),
            projects = sampleProjects(),
            searchQuery = "",
            onSearchQueryChange = {},
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    showBackground = true,
    name = "Profil - Dark Theme"
)
@Composable
fun ProfileScreenDarkPreview() {
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfileScreen(
            name = "Louis Baudat",
            role = "Développeur full-stack",
            location = "Reims, France",
            description = "En alternance depuis 1 an, où je réalise des applications en C#/Angular",
            skills = listOf("C#", "Angular"),
            projects = sampleProjects(),
            searchQuery = "angular",
            onSearchQueryChange = {},
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
