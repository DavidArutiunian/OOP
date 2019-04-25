package labs.lab3.calculator.graphs

interface IGraph<V, E> {
    fun addVertex(v: V)

    fun addEdge(from: V, to: V?)

    fun containsVertex(v: V): Boolean

    fun outgoingEdgesOf(v: V): MutableSet<E>

    fun incomingEdgesOf(v: V): MutableSet<E>

    fun getEdgeSource(e: E): V

    fun getEdgeTarget(e: E): V

    fun getAllNeighbours(v: V): Set<V>
}
