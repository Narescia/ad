using MySql.Data.MySqlClient;
using System;
using System.Data;

namespace CMySql{
    class MainClass {

        private static IDbConnection connection;

        public static void Main(string[] args){

            string connectionString = "server=localhost;database=dbprueba;user=root;password=sistemas";
            connection = new MySqlConnection(connectionString);
            connection.Open();
            //nuevaCategoria();
            leerCategorias();
            eliminarCategoria();
            //modificarCategoria();

            connection.Close();
        }
        private static void leerCategorias(){
            IDbCommand dbCommand = connection.CreateCommand();
            dbCommand.CommandText = "select * from categoria";
            IDataReader dataReader = dbCommand.ExecuteReader();

            while (dataReader.Read())
                Console.WriteLine("id={0} nombre={1}", dataReader["id"], dataReader["nombre"]);
                dataReader.Close();
		}             

		private static void nuevaCategoria(){
            IDbCommand dbCommand = connection.CreateCommand();
            //dbCommand.CommandText = "insert into categoria(nombre) values('categoría 5')";
            dbCommand.CommandText = "insert into categoria(nombre) values(@nombre)";

            addParameter(dbCommand, "nombre", "categoría 6");  

            dbCommand.ExecuteNonQuery();
		}

        private static void addParameter(IDbCommand dbCommand, string name, object value){
			IDbDataParameter dbDataParameter = dbCommand.CreateParameter();
			dbDataParameter.ParameterName = name;
			dbDataParameter.Value = value;
			dbCommand.Parameters.Add(dbDataParameter);
        }

		private static void eliminarCategoria(){
            IDbCommand dbCommand = connection.CreateCommand();
            dbCommand.CommandText = "delete from categoria where id = 4;";
            int filasAfectadas = dbCommand.ExecuteNonQuery();
        }    

	    private static void modificarCategoria(){
		    IDbCommand dbCommand = connection.CreateCommand();
            dbCommand.CommandText = "update categoria set nombre = ‘categoría 4’ where id = 4";
            dbCommand.ExecuteNonQuery();
		}
    }
 }

