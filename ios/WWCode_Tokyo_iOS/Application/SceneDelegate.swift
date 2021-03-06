import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    
    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = scene as? UIWindowScene else { return }
        
        window = UIWindow(windowScene: windowScene)
        
        let navigationController = UINavigationController()
        let router = AppRouter(navigationController: navigationController)
        
        let http = NetworkHttp(baseUrl: Configuration.environment.baseURL, networkSession: URLSession.shared)
        let eventRepo = NetworkEventRepository(http: http)

        let viewController = EventListViewController(
            router: router,
            eventRepository: eventRepo,
            reloader: DefaultReloader()
        )

        navigationController.viewControllers = [viewController]
        
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }
}
