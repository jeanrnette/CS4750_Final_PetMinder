package com.example.petminder.ui.onboarding
import androidx.compose.foundation.layout.Arrangement

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.example.petminder.R

@Composable
fun PetInfoSummaryScreen(onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    fun checkPermissionAndPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    101
                )
            } else pickImageLauncher.launch("image/*")
        } else {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    101
                )
            } else pickImageLauncher.launch("image/*")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo and App Name
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pet_logo),
                contentDescription = "Pet Minder Logo",
                modifier = Modifier
                    .size(42.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Pet Minder",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
        }

        // Welcome Title
        Text(
            text = "Welcome Kenia!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
        )

        // Upload Image Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { checkPermissionAndPickImage() },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Pet Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_upload),
                        contentDescription = "Upload Icon",
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Upload Image",
                        color = Color(0xFF333333),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // Pet Summary Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .padding(16.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Sprinkles",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF333333)
            )
            Text(
                text = "Birthday",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF333333),
                modifier = Modifier.padding(top = 8.dp)
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFFE6E6E6), RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .padding(top = 4.dp)
            ) {
                Text(text = "August 17, 2009", color = Color(0xFF333333))
            }

            Text(
                text = "Attributes:",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF333333),
                modifier = Modifier.padding(top = 16.dp)
            )

            FlowTags(
                tags = listOf("Dog", "3lbs", "Vaccinated", "Female", "German Shepherd")
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Finish Button
        Button(
            onClick = { onFinish() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF222222)),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(text = "Finish", color = Color.White, fontSize = 16.sp)
        }

        // Pagination Dots
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Dot(active = false)
            Dot(active = false)
            Dot(active = true)
        }
    }
}

@Composable
fun FlowTags(tags: List<String>) {
FlowRow(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
)
 {
        tags.forEach { tag ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(0xFFE6E6E6), RoundedCornerShape(24.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_paw_black),
                    contentDescription = "Paw",
                    tint = Color(0xFF333333),
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp)
                )
                Text(text = tag, color = Color(0xFF333333), fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun Dot(active: Boolean) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(if (active) Color(0xFF000000) else Color(0xFFD3D3D3))
            .padding(4.dp)
    )
}
