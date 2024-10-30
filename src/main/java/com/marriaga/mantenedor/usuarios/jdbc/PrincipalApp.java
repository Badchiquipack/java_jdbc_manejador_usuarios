package com.marriaga.mantenedor.usuarios.jdbc;

import com.marriaga.mantenedor.usuarios.jdbc.modelo.Usuario;
import com.marriaga.mantenedor.usuarios.jdbc.repositorio.IRepositorio;
import com.marriaga.mantenedor.usuarios.jdbc.repositorio.UsuarioRepositorioImpl;
import com.marriaga.mantenedor.usuarios.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PrincipalApp {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int opcion = 0;

        try (Connection conn = ConexionBaseDatos.getInstance()) {

            IRepositorio<Usuario> repositorio = new UsuarioRepositorioImpl();

            while (opcion != 6) {
                System.out.println("Seleccione la opción deseada: \n" +
                        "1.- Listar usuarios\n2.- Buscar usuario por Id\n3.- Crear nuevo usuario\n" +
                        "4.- Editar usuario\n5.- Eliminar usuario\n6.-Salir");
                opcion = s.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("================ Listar Todos ================");
                        repositorio.listar().forEach(System.out::println);
                        System.out.println("==============================================");
                        break;
                    case 2:
                        System.out.println("=============== Usuario por Id ===============");
                        System.out.println("Indique el íd del usuario que desea consultar:");
                        Long id = s.nextLong();
                        if (repositorio.porId(id)!=null) {
                            System.out.println(repositorio.porId(id));
                        }else {
                            System.out.println("El usuario con el id proporcionado no existe");
                        }
                        System.out.println("==============================================");
                        break;
                    case 3:
                        System.out.println("============== Agregar Usuario ==============");
                        Usuario usuario = new Usuario();
                        System.out.println("Ingrese el username del nuevo usuario:");
                        usuario.setUsername(s.next());
                        System.out.println("Ingrese el password del nuevo usuario:");
                        usuario.setPassword(s.next());
                        System.out.println("Ingrese el email del nuevo usuario:");
                        usuario.setEmail(s.next());

                        repositorio.guardar(usuario);

                        System.out.println("Usuario guardado con éxito!");
                        System.out.println("==============================================");
                        break;
                    case 4:
                        System.out.println("============== Modificar Usuario ==============");
                        Usuario usuario2 = new Usuario();
                        System.out.println("Ingrese el id del usuario que desea modificar:");
                        usuario2.setId(s.nextLong());
                        if (repositorio.porId(usuario2.getId())!=null){
                            System.out.println("Ingrese el nuevo username del usuario:");
                            usuario2.setUsername(s.next());
                            System.out.println("Ingrese el nuevo password del usuario:");
                            usuario2.setPassword(s.next());
                            System.out.println("Ingrese el nuevo email del usuario:");
                            usuario2.setEmail(s.next());

                            repositorio.guardar(usuario2);
                            System.out.println("Usuario modificado exitosamente");
                        }else {
                            System.out.println("El usuario con el id proporcionado no existe");
                        }
                        System.out.println("==============================================");
                        break;
                    case 5:
                        System.out.println("============== Eliminar Usuario ==============");
                        System.out.println("Ingresa el id del usuario que deseas eliminar:");
                        Long id2 = s.nextLong();
                        if (repositorio.porId(id2)!=null) {
                            repositorio.eliminar(id2);
                            System.out.println("Usuario eliminado exitosamente");
                        }else {
                            System.out.println("El usuario con el id ingresado no existe");
                        }
                        System.out.println("==============================================");
                        break;
                    case 6:
                        System.out.println("Gracias por usar este sistema. Adiós!");
                    default:
                        System.err.println("Debes elegir una opción válida");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
