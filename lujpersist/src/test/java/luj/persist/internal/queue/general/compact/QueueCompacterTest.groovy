package luj.persist.internal.queue.general.compact

import luj.persist.internal.queue.general.QueueMergeHandler
import spock.lang.Specification

class QueueCompacterTest extends Specification {

  List _elemList

  def "Compact:两个合成一个"() {
    given:
    _elemList = [
        'remove',
        'create',
        'other',
    ]

    when:
    def result = compact()

    then:
    result == [
        'update',
        'other',
    ]
  }

  def "Compact:三个合成一个"() {
    given:
    _elemList = [
        'other',
        'update',
        'remove',
        'create',
    ]

    when:
    def result = compact()

    then:
    result == [
        'other',
        'update',
    ]
  }

  List compact() {
    return new QueueCompacter(_elemList, [
        CreateMerger,
        UpdateMerger,
    ].collect { c -> c.newInstance() }).compact()
  }

  class CreateMerger implements QueueMergeHandler {
    @Override
    boolean canHandle(Object elem) {
      return elem == 'create'
    }

    @Override
    Result onMerge(Context ctx) {
      if (ctx.beforeElement == 'remove') {
        return ctx.result()
            .replaceBefore([])
            .replaceCurrent(['update'])
      }
      return ctx.result().skip()
    }
  }

  class UpdateMerger implements QueueMergeHandler {
    @Override
    boolean canHandle(Object elem) {
      return elem == 'update'
    }

    @Override
    Result onMerge(Context ctx) {
      if (ctx.beforeElement == 'update') {
        return ctx.result()
            .replaceBefore([])
            .replaceCurrent(['update'])
      }
      return ctx.result().skip()
    }
  }
}
