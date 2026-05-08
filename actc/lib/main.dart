import 'package:flutter/material.dart';

void main() => runApp(const PrimeraApp());

class PrimeraApp extends StatelessWidget {
  const PrimeraApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Primera App',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.lightBlue),
      ),
      home: const PaginaInicio(),
    );
  }
}

class PaginaInicio extends StatefulWidget {
  const PaginaInicio({super.key});

  @override
  State<PaginaInicio> createState() => _PaginaInicioState();
}


class _PaginaInicioState extends State<PaginaInicio>
    with SingleTickerProviderStateMixin {

  final TextEditingController _controller = TextEditingController();
  late AnimationController _animCtrl;
  late Animation<double> _rotacion;
  bool _mostrarImg = false;
  //tema
  ThemeMode _modo = ThemeMode.light;
  @override
  void initState() {
    super.initState();
    //para el tiempo
    _animCtrl = AnimationController(
      vsync: this,
      duration: const Duration(seconds: 1),
    );
    _rotacion = Tween<double>(begin: 0, end: 1).animate(_animCtrl);//para controlar la animacion
  }

  @override
  void dispose() {
    _controller.dispose();
    _animCtrl.dispose();
    super.dispose();
  }

  void _alPresionar() async {
    setState(() => _mostrarImg = true);//cambia el estado del booleano a true
    _animCtrl.forward(from: 0);
    await Future.delayed(const Duration(seconds: 10));//para que la imagen no se quede td el rato en la pantalla
    setState(() => _mostrarImg = false);
    _animCtrl.reset();
  }

  @override
  Widget build(BuildContext context) {
    final colors = Theme.of(context).colorScheme;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Primera app Flutter'),
        backgroundColor: colors.primaryContainer,
        foregroundColor: colors.onPrimaryContainer,
      ),
      body: Stack(
        children: [
          Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              children: [
                IconButton(
                  icon: const Icon(Icons.eighteen_mp, size: 72),
                  onPressed: () {
                    _modo = ThemeMode.dark;
                  },
                ),
                TextField(
                  controller: _controller,
                  autofocus: true,
                  maxLength: 15,
                  textCapitalization: TextCapitalization.sentences,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    prefixIcon: Icon(Icons.account_box),
                    labelText: 'Usuario',
                    helperText: 'Nombre de usuario',
                  ),
                ),
                const SizedBox(height: 16),
                ElevatedButton(
                  onPressed: _mostrarImg ? null : _alPresionar,
                  child: const Text("Submit"),
                ),
              ],
            ),
          ),

          if (_mostrarImg)
            RotationTransition(
              turns: _rotacion,//para que de una vuelta
              child: SizedBox.expand(
                child: Image.network(
                  "https://eforms.com/images/2018/03/Employment-Job-Application-791x1024.png",
                  fit: BoxFit.cover,//cubre td
                ),
              ),
            ),
        ],
      ),
    );
  }
}

