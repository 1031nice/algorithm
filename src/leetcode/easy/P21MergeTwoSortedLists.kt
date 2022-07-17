package leetcode.easy

fun main() {
    val problem = P21MergeTwoSortedLists()

    val list1 = ListNode(1)
    list1.next = ListNode(2)
    list1.next!!.next = ListNode(4)

    val list2 = ListNode(1)
    list2.next = ListNode(3)
    list2.next!!.next = ListNode(4)

    var list = problem.mergeTwoLists(list1, list2)
    while (list != null) {
        println(list.`val`)
        list = list.next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class P21MergeTwoSortedLists {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var listA = list1
        var listB = list2

        if (listA == null || listB == null) {
            return listA ?: listB
        }

        val head = ListNode(0)
        var current = head
        while (true) {
            if (listA!!.`val` < listB!!.`val`) {
                current.`val` = listA.`val`
                listA = listA.next
            } else {
                current.`val` = listB.`val`
                listB = listB.next
            }

            if (listA == null || listB == null) {
                break
            }

            current.next = ListNode(0)
            current = current.next!!
        }

        current.next = listA ?: listB

        return head
    }
}