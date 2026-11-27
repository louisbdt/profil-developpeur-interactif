package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileAvatar(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Photo de profil",
            modifier = Modifier.size(96.dp)
        )
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(4.dp),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Text(
                text = "Disponible",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    location: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Localisation",
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = location,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun ProfileHeaderCard(
    name: String,
    role: String,
    location: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileAvatar()
                Spacer(modifier = Modifier.width(16.dp))
                ProfileHeader(
                    name = name,
                    role = role,
                    location = location,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SkillChip(
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun SkillsRow(
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(skills) { skill ->
            SkillChip(label = skill)
        }
    }
}

data class ExperienceUi(
    val title: String,
    val company: String,
    val period: String,
    val description: String
)

fun sampleExperiences(): List<ExperienceUi> {
    return listOf(
        ExperienceUi(
            title = "Développeur full-stack",
            company = "Alternance en entreprise",
            period = "2024 - 2026",
            description = "Développement d'applications internes en C#/Angular, du backend à l'interface utilisateur."
        ),
        ExperienceUi(
            title = "Site portfolio personnel",
            company = "Projet personnel",
            period = "2024",
            description = "Développement d'un site vitrine avec React et TypeScript pour présenter mes projets."
        ),
        ExperienceUi(
            title = "Projet académique",
            company = "IUT de Reims",
            period = "2023",
            description = "Réalisation d'une application web avec Symfony et React dans le cadre de la formation."
        )
    )
}

@Composable
fun ExperienceCard(
    experience: ExperienceUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = experience.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${experience.company} • ${experience.period}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = experience.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun ContactSection(
    email: String,
    githubLink: String,
    availability: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Contact",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = githubLink,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "GitHub") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = availability,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Disponibilité") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ContactButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = label)
    }
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    location: String,
    description: String,
    skills: List<String>,
    experiences: List<ExperienceUi>,
    email: String,
    contact: String,
    availability: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
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
                    text = "Expériences",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            items(experiences) { experience ->
                ExperienceCard(experience = experience)
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
fun ProfileScreen(modifier: Modifier = Modifier) {
    ProfileCard(
        name = "Louis Baudat",
        role = "Étudiant en développement web à l'ESGI",
        location = "Reims, France",
        description = "Étudiant en développement web à l'ESGI. Je m'intéresse particulièrement au développement full-stack, de Symfony/PHP/C# côté backend à React/TypeScript/Angular côté frontend.",
        skills = listOf("Symfony", "React", "TypeScript", "C#","Angular"),
        experiences = sampleExperiences(),
        email = "contact@louisbaudat.dev",
        contact = "github.com/louisbdt",
        availability = "Disponible pour une alternance",
        onContactClick = {

        },
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileCardJuniorPreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Louis Baudat",
            role = "Étudiant en développement web à l'ESGI",
            location = "Reims, France",
            description = "Je découvre actuellement Android et Jetpack Compose.",
            skills = listOf("Symfony", "React", "TypeScript"),
            experiences = sampleExperiences(),
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardFullStackPreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Louis Baudat",
            role = "Développeur full-stack",
            location = "Reims, France",
            description = "En alternance depuis 1 an, où je réalise des applications en C#/Angular",
            skills = listOf("C#", "Angular"),
            experiences = sampleExperiences(),
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}

@Preview(
    showBackground = true,
    name = "Profil - Light Theme"
)
@Composable
fun ProfileCardLightPreview() {
    ProfilDeveloppeurTheme(darkTheme = false) {
        ProfileCard(
            name = "Louis Baudat",
            role = "Développeur full-stack",
            location = "Reims, France",
            description = "En alternance depuis 1 an, où je réalise des applications en C#/Angular",
            skills = listOf("C#", "Angular"),
            experiences = sampleExperiences(),
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}

@Preview(
    showBackground = true,
    name = "Profil - Dark Theme"
)
@Composable
fun ProfileCardDarkPreview() {
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfileCard(
            name = "Louis Baudat",
            role = "Développeur full-stack",
            location = "Reims, France",
            description = "En alternance depuis 1 an, où je réalise des applications en C#/Angular",
            skills = listOf("C#", "Angular"),
            experiences = sampleExperiences(),
            email = "contact@louisbaudat.dev",
            contact = "github.com/louisbdt",
            availability = "Disponible pour une alternance",
            onContactClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}