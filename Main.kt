import java.util.UUID

// =====================================================================
// 1. DATA CLASS REQUIREMENT
// =====================================================================
data class Post(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var content: String,
    val author: String,
    val timestamp: Long = System.currentTimeMillis()
)

// =====================================================================
// 4. COLLECTIONS REQUIREMENT
// =====================================================================
val blogfeed = mutableListOf<Post>()

// =====================================================================
// 2. FUNCTIONS REQUIREMENT: Adding a post
// =====================================================================
fun createPost(title: String, content: String, author: String): Post? {
    // 3. CONDITIONALS REQUIREMENT: Error validation checks
    if (title.isBlank() || content.isBlank()) {
        println("⚠️ Error: Title and content cannot be empty! Post rejected.")
        return null
    }
    
    val newPost = Post(title = title, content = content, author = author)
    blogfeed.add(newPost)
    println("✅ Success: '${title}' has been published.")
    return newPost
}

// =====================================================================
// 2. FUNCTIONS REQUIREMENT: Displaying all feed posts
// =====================================================================
fun displayFeed() {
    println("\n=====================================================")
    println("📝 RECENT POSTS FEED")
    println("=====================================================")
    
    if (blogfeed.isEmpty()) {
        println("The feed is empty. Write something amazing first!")
        return
    }
    
    // 4. LOOPS REQUIREMENT: Iterating through the posts collection
    for (post in blogfeed) {
        // 5. EXPRESSIONS REQUIREMENT: Dynamic word count calculation
        val wordCount = post.content.split("\\s+".toRegex()).size
        
        println("🆔 ID: ${post.id}")
        println("📌 Title: ${post.title}")
        println("📝 Content: ${post.content}")
        println("✍️ Author: ${post.author}")
        println("🕒 Timestamp: ${post.timestamp}")
        println("🔢 WORDS: $wordCount")
        println("-----------------------------------------------------")
    }
}

// =====================================================================
// 2. FUNCTIONS REQUIREMENT: Updating a post's content by ID
// =====================================================================
fun updatePost(id: String, newContent: String) {
    if (newContent.isBlank()) {
        println("⚠️ Error: Content cannot be blank during an update.")
        return
    }

    val post = blogfeed.find { it.id == id }
    if (post != null) {
        post.content = newContent
        println("✏️ Post '${post.title}' updated successfully.")
    } else {
        println("⚠️ Update failed: Post not found.")
    }
}

// =====================================================================
// 2. FUNCTIONS REQUIREMENT: Deleting a post by ID
// =====================================================================
fun deletePost(id: String) {
    val removed = blogfeed.removeIf { it.id == id }
    if (removed) {
        println("🗑️ Post deleted successfully.")
    } else {
        println("⚠️ Deletion failed: Post not found.")
    }
}

// =====================================================================
// INTERACTIVE TERMINAL ENGINE (No automated tests!)
// =====================================================================
fun main() {
    var running = true

    println("--- Welcome to the blandineTech Interactive Blog Terminal ---")

    while (running) {
        println("\nChoose an option:")
        println("1. Create a Post")
        println("2. View Blog Feed")
        println("3. Update a Post")
        println("4. Delete a Post")
        println("5. Exit")
        print("Enter choice (1-5): ")

        // readLine() pauses the terminal and waits for your keyboard input
        val choice = readLine()?.trim()

        when (choice) {
            "1" -> {
                println("\n--- Creating a New Post ---")
                print("Enter Title: ")
                val title = readLine() ?: ""
                print("Enter Content: ")
                val content = readLine() ?: ""
                print("Enter Author Name: ")
                val author = readLine() ?: ""
                
                // This calls your function using your manually typed values!
                createPost(title, content, author)
            }
            "2" -> {
                displayFeed()
            }
            "3" -> {
                print("Enter the ID of the post to update: ")
                val id = readLine() ?: ""
                print("Enter new content text: ")
                val newContent = readLine() ?: ""
                updatePost(id, newContent)
            }
            "4" -> {
                print("Enter the ID of the post to delete: ")
                val id = readLine() ?: ""
                deletePost(id)
            }
            "5" -> {
                println("Goodbye! Closing blandineTech Engine.")
                running = false
            }
            else -> {
                println("⚠️ Invalid input! Please type a number from 1 to 5.")
            }
        }
    }
}