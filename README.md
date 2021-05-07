# Github-Repo-Consumer

**Github-Repo-Consumer** is a small application to retrive information about github repositories using up to date tech-stack in Android development

## Project characteristics

- 100% [Kotlin](https://kotlinlang.org/)
- Model-View-ViewModel
- A single activity architecture
- [Android Jetpack](https://developer.android.com/jetpack)
- Dependency Injection

## Libraries

- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For managing background threads.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values.
- Jetpack
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI-related data that isn't destroyed on app rotations.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data objects that notify views when the underlying database changes.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client.
- [Glide](https://bumptech.github.io/glide/) - An image loading and caching library.
- [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework.
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API.


## Github API

Github-Repo-Consumer uses the [Github API](https://docs.github.com/en/rest) to get the information about the repositories and its owners.