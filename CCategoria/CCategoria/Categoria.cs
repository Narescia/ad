using System;
namespace CCategoria {
    public class Categoria {
        public Categoria() {
        }
        private long id;
        private string nombre = "";

        public long Id {
            get { return id; }
            set { id = value; }
        }

        public string Nombre {
            get { return nombre; }
            set { nombre = value; }
        }
    }
}
