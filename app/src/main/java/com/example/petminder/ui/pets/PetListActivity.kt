class PetListActivity : AppCompatActivity() {

    private lateinit var viewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_list)

        viewModel = ViewModelProvider(this)[PetViewModel::class.java]

        setupBottomNav()
        setupObservers()
        findViewById<FloatingActionButton>(R.id.btnAddPet).setOnClickListener {
            startActivity(Intent(this, AddPetActivity::class.java))
        }

    }

    private fun setupObservers() {
        viewModel.allPets.observe(this) { pets ->
            updateUI(pets)
        }
    }

    private fun updateUI(pets: List<PetEntity>) {
        val container = findViewById<LinearLayout>(R.id.mainContent)
        container.removeAllViews()

        pets.forEach { pet ->
            val card = layoutInflater.inflate(R.layout.item_pet_card, container, false)

            card.findViewById<TextView>(R.id.txtName).text = pet.name
            card.findViewById<TextView>(R.id.txtAge).text = pet.age

            val img = card.findViewById<ImageView>(R.id.imgPet)
            pet.imageUri?.let { img.setImageURI(Uri.parse(it)) }

            card.setOnClickListener {
                val intent = Intent(this, PetDetailsActivity::class.java)
                intent.putExtra("petId", pet.id)
                startActivity(intent)
            }

            container.addView(card)
        }
    }

    private fun setupBottomNav() {
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeDailyTaskActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navCalendar).setOnClickListener {
            startActivity(Intent(this, CalendarScreenActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
