package labs.lab3.calculator.graphs

import org.jgrapht.graph.DefaultEdge

@Suppress("unused")
class GraphFactory {
    companion object {
        fun <V> createJGraphTGraph(): IGraph<V, DefaultEdge> = JGraphTAdapter()

        fun <V> createCustomGraph(): IGraph<V, Pair<V, V>> = CustomGraphAdapter()
    }
}
