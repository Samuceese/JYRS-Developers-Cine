package org.example.demo.di



import org.example.demo.usuarios.cache.CacheUsuario
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.usuarios.repositories.UserRepositoryImpl
import org.example.demo.usuarios.services.UserService
import org.example.demo.usuarios.services.UserServiceImpl
import org.example.demo.view.controllers.LoginViewController
import org.example.demo.view.controllers.RegistroViewController
import org.example.demo.view.viewModel.LoginViewModel
import org.example.demo.view.viewModel.RegistroViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val appModule = module {
    single <CacheUsuario>{ CacheUsuario(5) }
    single <UserRepository>{ UserRepositoryImpl() }
    single <UserService>{ UserServiceImpl(get(),get()) }
    singleOf(:: LoginViewController)
    singleOf(:: LoginViewModel)
    singleOf(:: RegistroViewController)
    singleOf(:: RegistroViewModel)


}

