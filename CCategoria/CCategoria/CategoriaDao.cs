using System;
using MySql.Data.MySqlClient;
using System.Data;
using Serpis.Ad;

namespace CCategoria {
    public class CategoriaDao {
        
        public const string SelectAll = "select * from categoria order by id";

        public static Categoria Load (object id) {
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "select * from categoria where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
			IDataReader dataReader = dbCommand.ExecuteReader();
			dataReader.Read(); // TODO tratamiendo de excepciones
			string nombre = (string)dataReader["nombre"];
			dataReader.Close();

			Categoria categoria = new Categoria();
			categoria.Id = Convert.ToInt64(id);
            categoria.Nombre = nombre;
			return categoria;
		}

        public static void Save(Categoria categoria){
            if (categoria.Id == 0)
                insert(categoria);
            else
                update(categoria);

        }

        private static void insert (Categoria categoria){
			
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
            DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
			dbCommand.ExecuteNonQuery();
        }

        private static void update (Categoria categoria){
			
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "update categoria set nombre = @nombre where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
			DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
			dbCommand.ExecuteNonQuery();
        }

        public static void Delete(object id) {
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "delete from categoria where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
			dbCommand.ExecuteNonQuery();
        }


    }
}
