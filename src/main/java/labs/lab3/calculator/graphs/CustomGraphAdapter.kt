package labs.lab3.calculator.graphs

import java.util.*
import java.util.stream.Stream
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

class CustomGraphAdapter<V> : IGraph<V, Pair<V, V>> {
    private data class GraphNode<V>(
        val value: V,
        val children: MutableSet<V> = HashSet(),
        val parents: MutableSet<V> = HashSet()
    )

    private val nodes = HashMap<V, GraphNode<V>>()
    private val vertices = HashSet<V>()

    override fun addVertex(v: V) {
        val node = GraphNode(v)
        nodes[v] = node
        vertices.add(v)
    }

    override fun addEdge(from: V, to: V?) {
        when {
            !vertices.contains(from) -> throw IndexOutOfBoundsException("Unknown vertex '$from'")
            !vertices.contains(to) -> throw IndexOutOfBoundsException("Unknown vertex '$to'")
        }
        nodes[from]!!.children.add(to!!)
        nodes[to]!!.parents.add(from)
    }

    override fun containsVertex(v: V): Boolean {
        return vertices.contains(v)
    }

    override fun outgoingEdgesOf(v: V): MutableSet<Pair<V, V>> {
        if (!vertices.contains(v)) {
            throw IndexOutOfBoundsException("Unknown vertex '$v'")
        }
        return nodes[v]!!.children.map { Pair(v, it) }.toMutableSet()
    }

    override fun incomingEdgesOf(v: V): MutableSet<Pair<V, V>> {
        if (!vertices.contains(v)) {
            throw IndexOutOfBoundsException("Unknown vertex '$v'")
        }
        return nodes[v]!!.parents.map { Pair(it, v) }.toMutableSet()
    }

    override fun getEdgeSource(e: Pair<V, V>): V {
        return e.first
    }

    override fun getEdgeTarget(e: Pair<V, V>): V {
        return e.second
    }

    override fun getAllNeighbours(v: V): Set<V> {
        val result = LinkedHashSet<V>()
        val queue = ArrayDeque<V>()
        val visited = HashSet<V>()
        Stream.concat(
            outgoingEdgesOf(v).stream(),
            incomingEdgesOf(v).stream()
        )
            .map { listOf(it.first, it.second) }
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
                .map { listOf(it.first, it.second) }
                .flatMap(List<V>::stream)
                .filter { !queue.contains(it) }
                .filter { !visited.contains(it) }
                .forEach { queue.addFirst(it) }
        }
        return result
    }
}
