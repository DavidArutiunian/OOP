package labs.lab3.calculator.graphs

import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import java.util.*
import java.util.stream.Stream
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

class JGraphTAdapter<V> : IGraph<V, DefaultEdge> {
    private val graph = createGraph<DefaultEdge>()

    override fun addVertex(v: V) {
        graph.addVertex(v)
    }

    override fun addEdge(from: V, to: V?) {
        graph.addEdge(from, to)
    }

    override fun containsVertex(v: V) = graph.containsVertex(v)

    override fun outgoingEdgesOf(v: V): MutableSet<DefaultEdge> = graph.outgoingEdgesOf(v)

    override fun incomingEdgesOf(v: V): MutableSet<DefaultEdge> = graph.outgoingEdgesOf(v)

    override fun getEdgeSource(e: DefaultEdge): V = graph.getEdgeSource(e)

    override fun getEdgeTarget(e: DefaultEdge): V = graph.getEdgeTarget(e)

    override fun getAllNeighbours(v: V): Set<V> {
        val result = LinkedHashSet<V>()
        val queue = ArrayDeque<V>()
        val visited = HashSet<V>()
        Stream.concat(
            graph.incomingEdgesOf(v).stream(),
            graph.outgoingEdgesOf(v).stream()
        )
            .map { listOf(graph.getEdgeSource(it), graph.getEdgeTarget(it)) }
            .flatMap(List<V>::stream)
            .filter { !queue.contains(it) }
            .forEach { queue.add(it) }
        while (!queue.isEmpty()) {
            val curr = queue.first
            queue.pop()
            visited.add(curr)
            result.add(curr)
            Stream.concat(
                outgoingEdgesOf(curr).stream(),
                incomingEdgesOf(curr).stream()
            )
                .map { listOf(graph.getEdgeSource(it), graph.getEdgeTarget(it)) }
                .flatMap(List<V>::stream)
                .filter { !queue.contains(it) }
                .filter { !visited.contains(it) }
                .forEach { queue.addFirst(it) }
        }
        return result
    }

    private inline fun <reified T> createGraph() = SimpleDirectedGraph<V, T>(T::class.java)
}
