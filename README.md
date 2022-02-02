---
### Presentation [Link](https://otagopoly-my.sharepoint.com/:v:/g/personal/rosaf2_student_op_ac_nz/EZusDSAGgM1HrBGyvZRmaikBIxlH2m81hkoPasP_TAl7Pg?e=tEKRVr)
---

# Jetpack Compose

##### Overview
It is the new way of building user interfaces for android. It no longer makes use of xml layout files, but instead, we build our UI components (which are called Composables) in a declarative way. This approach gets rid of a lot of boilerplate code and makes the creation of apps easier, faster but above all a better developer experience.

##### Composables
Composables are functions annotated with `@Composable` which are in charge of creating a piece of the UI and cane ediyed independently. These composable functions can only be contained by other composable functions and by naming convention, the first letter must be upper case. These normally tend to be top-level functions in a file.

Example of a composable function:
```kotlin
@Composable
fun HelloCompose(greeting: String) {
    Text(text = greeting)
}
```

Where `Text("Hello Compose")` is a pre-built composable function.

One would normally want their composables to be small as they are easier to reuse.

##### MainActivity 

The MainActivity is much simpler in compose. It now inherits from `ComponentActivity()` and instead of associating a layout with `setContentView`, we just wrap our content as shown below. 
```kotlin
setContent {
    // Content
}
```

##### Previews
These portions of UI can be isolated and previewed in android studio.
To achieve this, we need to annotate the composable function with `@Preview` anotation which can also take parameters, for example:

```kotlin
@Preview(showBackground = true)
@Composable
fun HelloCompose(greeting: String = "Hello Compose") {
    Text(text = greeting)
}
```
We can see the preview by clicking on the Split or Design options.
The previews get rendered in real time, although we can always update it manually if it is outdated.

Bear in mind that in order to use previews, the composables that take parameters have to have a default values. We can also have multiple previews in one file.


##### Themes
To style our composables, android studio creates a theme using the name of our project by default. All we need to do is to wrap our content around our theme like:

```kotlin
    MyComposableTheme {
        // Content
    }
```

We have a folder structure with some files for colors, types, shapes and the main theme file.

```kotlin
    Text(
        text = "Hi!",
        style = MaterialTheme.typography.body1,
        shape = MaterialTheme.shapes.small // Internally RoundedCornerShape(4.dp)
    )
```

##### Modifiers
We can also tell the UI how to display or behave composables by using modifiers, for example:

```kotlin
    Text(
        text = "Hello again!",
        modifier = Modifier.padding(10.dp)
    )
```

It is important to know that the order of the modifiers matter.

##### Layout

These are special types of composables that allow us to arrange items in screens, for example:

```kotlin
Column (
    modifier = Modifier.padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text("Up")
    Text("Down")
}

Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Left")
    Text("Right")
}
```

##### Composable arguments
We can also pass composables as function parameters to other composables:

```kotlin
@Composable
fun Container(content: @Composable () -> Unit) {
    MyComposableTheme {
        content()
    }
}
```


##### Recomposition
In compose, only Composables whose data has changed are rerendered or recalled with the new data.

Let's say that we have the following code:

```kotlin
@Composable
fun Counter() {
    var counter = 0
    Button(onClick = { counter++ }) {
        Text("$counter times clicked.")
    }
}
```

We can see that the value of counter should change when the button is clicked, however, that is not the case. This is because the value is not able to survive that changes so when the composable gets rendered, the count value is set back to 0.

In order to overcome this, we need to somehow be able to remember such values and for that, Compose offers new data structures such as State and MutableState. The above code then could be rewritten as follows:

```kotlin
@Composable
fun Counter() {
    var counter by remember {
        mutableStateOf(0)
    }
    Button(onClick = { counter++ }) {
        Text("$counter times clicked.")
    }
}
```

In this case, `Counter()` creates an internal state as the value of counter gets remembered inside it making the composable **Stateful**, meaning we don't have access to this value outside of it.

##### State Hoisting

If for some reason we want to use the counter variable outside of `Counter()` we would need to "move up the state". This is also known as state hoisting. Hence, a stateless composable is a composable that does not hold any state.


Let's grab the last example and remove the state from `Count()` into `Container()`.

```kotlin
@Composable
fun Container() {
    var counter by remember {
        mutableStateOf(0)
    }
    Column {
        Counter(
            counter = counter,
            updateCounter = { newCounter: Int ->
                counter = newCounter
            }
        )
        if(counter > 5) Text("Clicked more than five times!")
    }
}

@Composable
fun Counter(counter: Int, updateCounter: (Int) -> Unit) {
    Button(onClick = { updateCounter(counter + 1) }) {
        Text("$counter times clicked.")
    }
}
```
Our `Counter` composable now takes two parameters. It is a common practice to pass a variable and a function when doing hoisting.

As a general rule of thumb, *"State is passed down and events passed up"*.



---
# Creating an app in Jetpack Compose!
With the acquired basic knowledge we can proceed and develop a tiny app. The app will have two screens (HomeScreen and RobotsScreen) and it will navigate between them.


##### ui.home.HomeScreen
```kotlin
@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            RobotCompanyCard()
            RobotCompanyText(navController)
            Container() // Already created above
        }
    }
}

@Composable
fun RobotCompanyCard() {
    Card(
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Image(
            painterResource(id = R.drawable.robotcompany),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun RobotCompanyText(navController: NavController) {
    Text(
        modifier = Modifier
            .padding(20.dp)
            .clickable {
                navController.navigate(route = Screen.Robots.route)
            },
        text = "Robot Company Inc.",
        color = MaterialTheme.colors.primary,
        fontSize = MaterialTheme.typography.h5.fontSize,
        fontWeight = FontWeight.Bold
    )
}
```


---
### Fetching Robots!

For the second screen I have created a simple API using [DiceBear Avatars](https://avatars.dicebear.com/) and implemented it doing the usual Retrofit fetch request as follows:

##### API Gist
[![whatever.png](https://avatars.dicebear.com/api/bottts/whatever.svg?size=70)](https://gist.github.com/ROSAF2/a1e6f9fca91465b3b4db4b122312abc8)  **Click the robot!**


##### build.gradle (Retrofit)

```gradle
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
```

##### AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET" />
<application
    ...
    android:usesCleartextTraffic="true"
>
    ...
</application>
```

##### Robot model
```kotlin
data class Robot(val code: String, val img: String, val position: String)
```

##### IRobot
```kotlin
interface IRobot {
    @GET("raw")
    suspend fun getResponse(): List<Robot>
}
```

##### RetrofitInstance
```kotlin
private const val BASE_URL =
    "https://gist.github.com/ROSAF2/a1e6f9fca91465b3b4db4b122312abc8/"

object ServiceInstance {
    val retrofitService: IRobot by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IRobot::class.java)
    }
}
```

### Basic Navigation
##### build.gradle (Navigation Compose)
```gradle
implementation "androidx.navigation:navigation-compose:2.5.0-alpha01"
```

##### navigation.Screen 
This sealed class only allows objects to inherit from it as long as such objects are contained within the sealed class. This helps us to register the routes we wnat in our application and nothing more.
```kotlin
sealed class Screen(val route: String) {
    object Home : Screen(route = "navigation_home")
    object Robots : Screen(route = "navigation_robots")
}
```

##### navigation.NavGraph
Here we set up our Navigation Graph by specifiying the navcontroller and the start destination. We also need to include the composable screens with its respective route.
```kotlin
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Robots.route) {
            RobotsScreen(
                navController = navController,
                robotsViewModel = viewModel<RobotsViewModel>()
            )
        }
    }
}
```
##### MainActivity
We define a navController instance and we set the Navigation Graph.
```kotlin
private lateinit var navController: NavHostController
...
navController = rememberNavController()
SetUpNavController(navController = navController)
```

Then we create a ViewModel to handle the state of our RobotsScreen.
##### ui.robots.RobotsViewModel
``` kotlin
class RobotsViewModel : ViewModel() {
    private val _robots: MutableState<List<Robot>> = mutableStateOf(listOf())
    val robots: State<List<Robot>> get() = _robots

    init {
        viewModelScope.launch {
            _robots.value = retrofitService.getResponse()
        }
    }
}
``` 

##### build.gradle (Coil)
This dependency allows us to modify Image composables and we will use it to fetch an image given its url like **Glide** usually does.
```gradle
implementation "io.coil-kt:coil-compose:1.4.0"
```

##### Animation

In compose we can easily animate composables for example, we can change the colour of a composable by defining a variable by `animateColorAsState` like in the example below:

##### ui.robots.RobotsItem
```kotlin
@Composable
fun RobotItem(robot: Robot) {
    var isSelected by rememberSaveable {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if(isSelected) MaterialTheme.colors.secondary else androidx.compose.ui.graphics.Color.Transparent, '' Set colour depending on isSelected state
        animationSpec = tween(300) // Fade transition time
    )

    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(20.dp, 10.dp)
            .clickable { isSelected = !isSelected },
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface(color = targetColor) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = robot.img,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.placeholder)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Column {
                    Text(
                        text = robot.code,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = robot.position,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}
```

It is important to point out that in order to make composables survive configuration changes we need to use `rememberSaveable` instead of just `remember` when we delegate.


##### ui.robots.RobotsList
In compose we no longer use recyclerviews, instead we use LazyColumn composables which do not recycle the items, but instead, it emits new composables which are cheap to make, so we still keep it optimal. 

```kotlin
@Composable
fun RobotList(robots: List<Robot>) {
    LazyColumn {
        items(robots) {
            RobotItem(robot = it)
        }
    }
}
```

##### ui.robots.RobotsScreen
Finally our RobotsScreen, which is in charge of displaying our navigation title "Members" and our robot list. In order to return to the HomeScreen and avoid keeping staking screens on the backstack we have to use `popUpTo("my_route_name") { inclusive = true }`
```kotlin
@Composable
fun RobotsScreen(navController: NavController, robotsViewModel: RobotsViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.clickable {
                    // Pop everything up to and including the "Home" destination off
                    // the back stack before navigating to the "Home" destination
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                text = "Members",
                color = MaterialTheme.colors.secondary,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
            RobotList(robots = robotsViewModel.robots.value)
        }
    }
}
```

### Conclusion
The declarative approach of Jetpack Compose offers a lot of familiarity for those into web developement, it also makes the code more manageble as all the UI can be programatically created. Additionally, it also includes several features right off the bat like recyclerview or modifier animations which make things way easier than before. But above all, the developement experience that it delivers is a strong point for those who are planning to develop their next big project. 

Happy Composing!.

### Citations
* Android Developers. (2021, February 24). *Jetpack Compose: State* [Video]. YouTube. https://www.youtube.com/watch?v=mymWGMy9pYI
* Android Developers. (2021b, May 24). Jetpack Compose basics | Workshop [Video]. YouTube. https://www.youtube.com/watch?v=qvDo0SKR8-k
* Google. (2022a, January 27). Compose layout basics | Jetpack Compose |. Android Developers. https://developer.android.com/jetpack/compose/layouts/basics
* Google. (2021, November 12). State and Jetpack Compose |. Android Developers. https://developer.android.com/jetpack/compose/state#viewmodel-state
* Google. (2022, January 27). *Navigating with Compose | Jetpack Compose |*. Android Developers. https://developer.android.com/jetpack/compose/navigation
* Stevdza-San. (2021, October 7). *Navigation Basics in Jetpack Compose* [Video]. YouTube. https://www.youtube.com/watch?v=glyqjzkc4fk
* Contributors, C. (2021). Jetpack Compose - Coil. Coil. https://coil-kt.github.io/coil/compose/