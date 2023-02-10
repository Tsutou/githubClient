## Architecture
- [アプリ アーキテクチャ ガイド](https://developer.android.com/topic/architecture?hl=ja)に沿う、ベーシックなMVVM

### UI
- Screen (Composableなページ)
- UiState (UIの状態を詰め込んだオブジェクト、StateFlowでUIに通知)
- Navigation Composeで遷移する(Graphは現段階でfeatureが少ないので不採用)

### Domain
- Repository (interface)
- Model (アプリ内固有のモデル、APIから取得した値をマップする)

### Data
- RepositoryImpl (Repositoryの実装, データの呼び出し、操作)
- Api (ApolloClientからBackendとやりとりする)

<img src="https://user-images.githubusercontent.com/29670270/217481339-18315146-27e2-4b89-8aba-419b02191f46.png" width="300">

## Spec

- [GitHub GraphQL API](https://docs.github.com/en/graphql)
- min sdk version = 24
- target sdk version = latest released version(33)
- Kotlin ,Kotlin Coroutines, Kotlin Coroutine Flow
- Android Jetpack
- Jetpack Compose, accompanist 

## Demo

https://user-images.githubusercontent.com/29670270/217481276-c9fade5a-6399-404e-a85a-95cfac6f0882.mp4