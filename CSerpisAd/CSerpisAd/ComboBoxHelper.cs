using System;
using System.Data;
using Gtk;
namespace CSerpisAd {
    public class ComboBoxHelper {
        public static void Fill(TreeView treeView, string selectsql) {

				IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
				dbCommand.CommandText = selectsql;
				IDataReader dataReader = dbCommand.ExecuteReader();
				init(treeView, dataReader);
				ListStore listStore = (ListStore)treeView.Model;
				fillListStore(listStore, dataReader);
				dataReader.Close();
			}

		public static object GetId(TreeView treeView) {
				TreeIter treeIter;
				treeView.Selection.GetSelected(out treeIter);
				return treeView.Model.GetValue(treeIter, 0);
		}
    }

}
