using Gtk;
using System;
using System.Data;
namespace Serpis.Ad {
	public class TreeViewHelper {
        
		private static void init(TreeView treeView, IDataReader dataReader) {
			if (treeView.Model != null)
				return;
			int fieldCount = dataReader.FieldCount;
			Type[] types = new Type[fieldCount];
			for (int index = 0; index < fieldCount; index++) {
				treeView.AppendColumn(dataReader.GetName(index),
									 new CellRendererText(), "text", index);

				types[index] = typeof(string);
			}
			ListStore listStore = new ListStore(types);
			treeView.Model = listStore;
		}

		private static void fillListStore(ListStore listStore, IDataReader dataReader) {
			listStore.Clear();
			int fieldCount = dataReader.FieldCount;
			while (dataReader.Read()) {
				string[] values = new string[fieldCount];
				for (int index = 0; index < fieldCount; index++)
					values[index] = dataReader[index].ToString();
				listStore.AppendValues(values);
			}
		}

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
